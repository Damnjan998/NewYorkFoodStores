package com.damnjan.nystores.service;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.exception.IncorrectConditionForNameOrAddressException;
import com.damnjan.nystores.exception.IncorrectLatitudeOrLongitudeException;
import com.damnjan.nystores.exception.InvalidPageOrSizeException;
import com.damnjan.nystores.mapper.StoreMapper;
import com.damnjan.nystores.model.responseModel.PageResponseModel;
import com.damnjan.nystores.model.responseModel.StoreDto;
import com.damnjan.nystores.repository.StoreRepository;
import com.damnjan.nystores.validator.StoreValidatorService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreValidatorService storeValidator;

    public StoreService(StoreRepository storeRepository, StoreValidatorService storeValidator) {
        this.storeRepository = storeRepository;
        this.storeValidator = storeValidator;
    }

    public void populateES() {
        List<Store> storeList = new ArrayList<>();

        try {
            storeList = new CsvToBeanBuilder(new FileReader("stores.csv"))
                    .withType(Store.class)
                    .build()
                    .parse();

        } catch (FileNotFoundException e) {

            log.error("There is a problem while reading CSV file. ", e);
        }

        storeRepository.saveAll(storeList);
    }

    public PageResponseModel<StoreDto> getClosestStore(Double latitude, Double longitude, Integer page, Integer size)
            throws IncorrectLatitudeOrLongitudeException, InvalidPageOrSizeException {

        storeValidator.checkLatitudeAndLongitude(latitude, longitude);
        storeValidator.checkPageOrSize(page, size);
        return null;
    }

    public PageResponseModel<StoreDto> getStoreByNameOrAddress(String condition, Integer page, Integer size)
            throws IncorrectConditionForNameOrAddressException, InvalidPageOrSizeException {

        storeValidator.checkNameOrAddress(condition);
        storeValidator.checkPageOrSize(page, size);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Store> stores = storeRepository.findByEntityNameContainingOrStreetNameContaining(condition, condition, pageable);
        List<StoreDto> storeDtos = StoreMapper.INSTANCE.storesToStoreDto(stores);
        return new PageResponseModel<>(storeDtos, stores.getTotalPages(), stores.getNumber() + 1, stores.getTotalElements());

    }
}
