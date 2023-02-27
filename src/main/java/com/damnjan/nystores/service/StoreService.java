package com.damnjan.nystores.service;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.mapper.StoreMapper;
import com.damnjan.nystores.model.StoreCSV;
import com.damnjan.nystores.model.responseModel.PageResponseModel;
import com.damnjan.nystores.model.responseModel.StoreDto;
import com.damnjan.nystores.repository.StoreRepository;
import com.damnjan.nystores.validator.StoreValidatorService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoreService {

    private static final String LOCATION = "location";
    private final StoreRepository storeRepository;
    private final StoreValidatorService storeValidator;
    private final ElasticsearchOperations elasticsearchOperations;

    public StoreService(StoreRepository storeRepository, StoreValidatorService storeValidator, ElasticsearchOperations operations) {
        this.storeRepository = storeRepository;
        this.storeValidator = storeValidator;
        this.elasticsearchOperations = operations;
    }

    public void populateES() {
        List<Store> storeList = new ArrayList<>();

        try {
            List<StoreCSV> storeCsv = new CsvToBeanBuilder(new FileReader("stores.csv"))
                    .withType(StoreCSV.class)
                    .build()
                    .parse();
            storeList = StoreMapper.INSTANCE.storesCsvToStores(storeCsv);
        } catch (FileNotFoundException e) {

            log.error("There is a problem while reading CSV file. ", e);
        }

        storeRepository.saveAll(storeList);
    }

    public PageResponseModel<StoreDto> getClosestStore(double lat, double lon, int distance, String unit,
                                                       int page, int size) {

        storeValidator.checkLatitudeAndLongitude(lat, lon);
        storeValidator.checkPageOrSize(page, size);
        storeValidator.checkDistanceAndUnit(distance, unit);

        GeoPoint geoPoint = new GeoPoint(lat, lon);

        Query query = new CriteriaQuery(new Criteria(LOCATION).within(geoPoint, distance + unit));

        Pageable pageable = PageRequest.of(page - 1, size);

        Sort sort = Sort.by(new GeoDistanceOrder(LOCATION, geoPoint).withUnit(unit));

        query.addSort(sort);
        query.setPageable(pageable);

        SearchHits<Store> searchHits = elasticsearchOperations.search(query, Store.class);
        SearchPage<Store> storeSearchPage = SearchHitSupport.searchPageFor(searchHits, query.getPageable());

        List<StoreDto> storeDtos = StoreMapper.INSTANCE.searchStoresToStoreDto(storeSearchPage.getSearchHits()
                .getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList()));
        return new PageResponseModel<>(storeDtos, storeSearchPage.getTotalPages(),
                storeSearchPage.getNumber() + 1, storeSearchPage.getTotalElements());
    }

    public PageResponseModel<StoreDto> getStoreByNameOrAddress(String condition, int page, int size) {

        storeValidator.checkNameOrAddress(condition);
        storeValidator.checkPageOrSize(page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Store> stores = storeRepository.findByEntityNameContainingOrStreetNameContaining(condition, condition, pageable);
        List<StoreDto> storeDtos = StoreMapper.INSTANCE.storesToStoreDto(stores);
        return new PageResponseModel<>(storeDtos, stores.getTotalPages(), stores.getNumber() + 1, stores.getTotalElements());

    }
}
