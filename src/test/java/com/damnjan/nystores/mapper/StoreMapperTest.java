package com.damnjan.nystores.mapper;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.model.responseModel.StoreDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreMapperTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final StoreMapper storeMapper = Mappers.getMapper(StoreMapper.class);

    @Test
    void should_map_stores_to_storeDto() throws IOException {

        JavaType storeType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
                Store.class);

        JavaType storeDtoType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
                StoreDto.class);

        List<Store> stores = objectMapper.readValue(new ClassPathResource("store_list.json")
                .getInputStream(), storeType);

        List<StoreDto> storeDtos = objectMapper.readValue(new ClassPathResource("store_dto_list.json")
                .getInputStream(), storeDtoType);


        List<StoreDto> mappedStoreDto = storeMapper.searchStoresToStoreDto(stores);

        assertEquals(mappedStoreDto.get(0).getClass(), storeDtos.get(0).getClass());
        assertEquals(mappedStoreDto.get(0).getCity(), storeDtos.get(0).getCity());
        assertEquals(mappedStoreDto.get(0).getCounty(), storeDtos.get(0).getCounty());
        assertEquals(mappedStoreDto.get(0).getDbaName(), storeDtos.get(0).getDbaName());
        assertEquals(mappedStoreDto.get(0).getStreetName(), storeDtos.get(0).getStreetName());
        assertEquals(mappedStoreDto.get(0).getStreetNumber(), storeDtos.get(0).getStreetNumber());
        assertEquals(mappedStoreDto.get(0).getEntityName(), storeDtos.get(0).getEntityName());
        assertEquals(mappedStoreDto.get(0).getId(), storeDtos.get(0).getId());
        assertEquals(mappedStoreDto.get(0).getZipCode(), storeDtos.get(0).getZipCode());
    }

    @Test
    void should_map_page_stores_to_storeDto() throws IOException {

        JavaType pageStoreType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
                Store.class);

        JavaType storeDtoType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
                StoreDto.class);

        List<Store> stores = objectMapper.readValue(new ClassPathResource("store_list.json")
                .getInputStream(), pageStoreType);

        List<StoreDto> storeDtos = objectMapper.readValue(new ClassPathResource("store_dto_list.json")
                .getInputStream(), storeDtoType);

        Page<Store> pageStores = new PageImpl<>(stores);

        List<StoreDto> mappedStoreDto = storeMapper.storesToStoreDto(pageStores);

        assertEquals(mappedStoreDto.get(0).getClass(), storeDtos.get(0).getClass());
        assertEquals(mappedStoreDto.get(0).getCity(), storeDtos.get(0).getCity());
        assertEquals(mappedStoreDto.get(0).getCounty(), storeDtos.get(0).getCounty());
        assertEquals(mappedStoreDto.get(0).getDbaName(), storeDtos.get(0).getDbaName());
        assertEquals(mappedStoreDto.get(0).getStreetName(), storeDtos.get(0).getStreetName());
        assertEquals(mappedStoreDto.get(0).getStreetNumber(), storeDtos.get(0).getStreetNumber());
        assertEquals(mappedStoreDto.get(0).getEntityName(), storeDtos.get(0).getEntityName());
        assertEquals(mappedStoreDto.get(0).getId(), storeDtos.get(0).getId());
        assertEquals(mappedStoreDto.get(0).getZipCode(), storeDtos.get(0).getZipCode());
    }
}