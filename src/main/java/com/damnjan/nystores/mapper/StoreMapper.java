package com.damnjan.nystores.mapper;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.model.StoreCSV;
import com.damnjan.nystores.model.responseModel.StoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public abstract class StoreMapper {

    public static StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    public abstract List<StoreDto> searchStoresToStoreDto(List<Store> stores);

    public abstract List<StoreDto> storesToStoreDto(Page<Store> stores);

    public abstract StoreDto storeToStoreDto(Store store);

    public abstract List<Store> storesCsvToStores(List<StoreCSV> storeCsv);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "location",
            expression = "java(storeCSV.getLatitude() == null || storeCSV.getLongitude() == null ? null :" +
                    " new org.springframework.data.elasticsearch.core.geo.GeoPoint(storeCSV.getLatitude(), storeCSV.getLongitude()))")
    public abstract Store storeCsvToStore(StoreCSV storeCSV);
}
