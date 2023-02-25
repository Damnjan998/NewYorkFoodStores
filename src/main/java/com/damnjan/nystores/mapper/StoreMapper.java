package com.damnjan.nystores.mapper;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.model.responseModel.StoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public abstract class StoreMapper {

    public static StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    public abstract List<StoreDto> storesToStoreDto(Page<Store> stores);
    public abstract StoreDto storeToStoreDto(Store store);
}
