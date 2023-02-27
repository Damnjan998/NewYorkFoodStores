package com.damnjan.nystores.service;

import com.damnjan.nystores.document.Store;
import com.damnjan.nystores.exception.NyStoreException;
import com.damnjan.nystores.mapper.StoreMapper;
import com.damnjan.nystores.mapper.StoreMapperImpl;
import com.damnjan.nystores.model.responseModel.PageResponseModel;
import com.damnjan.nystores.model.responseModel.StoreDto;
import com.damnjan.nystores.repository.StoreRepository;
import com.damnjan.nystores.util.ErrorMessages;
import com.damnjan.nystores.validator.StoreValidatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    StoreValidatorService storeValidatorService = new StoreValidatorService();
    StoreValidatorService storeValidatorServiceMock = mock(StoreValidatorService.class);
    StoreRepository storeRepository = mock(StoreRepository.class);
    StoreMapper storeMapper = new StoreMapperImpl();
    ElasticsearchOperations elasticsearchOperations = mock(ElasticsearchOperations.class);
    StoreService storeService = new StoreService(storeRepository, storeValidatorService, elasticsearchOperations);

    @Test
    void populateES() {
    }

    @Test
    void getClosestStore() {
    }

    @Test
    void should_return_stores_for_given_condition() throws IOException {

        // Given
        String condition = "a";
        int page = 1;
        int size = 20;

        StoreDto storeDtoBroome = objectMapper.readValue(new ClassPathResource("store_dto_Broome.json").getInputStream(), StoreDto.class);
        StoreDto storeDtoErie = objectMapper.readValue(new ClassPathResource("store_dto_Erie.json").getInputStream(), StoreDto.class);

        Store storeBroome = objectMapper.readValue(new ClassPathResource("store_dto_Broome.json").getInputStream(), Store.class);
        Store storeErie = objectMapper.readValue(new ClassPathResource("store_dto_Erie.json").getInputStream(), Store.class);

        List<StoreDto> storeDtos = Arrays.asList(storeDtoBroome, storeDtoErie);
        List<Store> stores = Arrays.asList(storeBroome, storeErie);
        PageImpl<Store> storePage = new PageImpl<>(stores);

        // When
        doNothing().when(storeValidatorServiceMock).checkNameOrAddress(condition);
        doNothing().when(storeValidatorServiceMock).checkPageOrSize(page, size);

        when(storeRepository.findByEntityNameContainingOrStreetNameContaining(anyString(), anyString(), any())).thenReturn(storePage);

        PageResponseModel<StoreDto> storeByNameOrAddress = storeService.getStoreByNameOrAddress(condition, page, size);

        // Then

        verify(storeRepository, times(1)).findByEntityNameContainingOrStreetNameContaining(anyString(), anyString(), any());

        assertEquals(storeByNameOrAddress.getResponse().get(0).getId(), storeDtos.get(0).getId());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getDbaName(), storeDtos.get(0).getDbaName());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getEntityName(), storeDtos.get(0).getEntityName());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getStreetName(), storeDtos.get(0).getStreetName());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getStreetNumber(), storeDtos.get(0).getStreetNumber());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getCounty(), storeDtos.get(0).getCounty());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getCity(), storeDtos.get(0).getCity());
        assertEquals(storeByNameOrAddress.getResponse().get(0).getClass(), storeDtos.get(0).getClass());
    }

    @Test
    void should_throw_invalid_condition_for_name_or_address() {

        int page = 1;
        int size = 10;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getStoreByNameOrAddress(null, page, size));

        assertEquals(ErrorMessages.INVALID_CONDITION_FOR_NAME_OR_ADDRESS.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_page_number() {

        String condition = "a";
        int page = 0;
        int size = 10;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getStoreByNameOrAddress(condition, page, size));

        assertEquals(ErrorMessages.PAGE_NUMBER_WRONG.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_size_number() {

        String condition = "a";
        int page = 1;
        int size = 0;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getStoreByNameOrAddress(condition, page, size));

        assertEquals(ErrorMessages.SIZE_NUMBER_WRONG.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_lat_or_lon() {

        double lat = 30;
        double lon = -200;
        int distance = 100;
        String unit = "km";
        int page = 1;
        int size = 20;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getClosestStore(lat, lon, distance, unit, page, size));

        assertEquals(ErrorMessages.INVALID_LAT_LON.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_distance() {

        double lat = 30;
        double lon = 50;
        int distance = -2;
        String unit = "km";
        int page = 1;
        int size = 20;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getClosestStore(lat, lon, distance, unit, page, size));

        assertEquals(ErrorMessages.INVALID_DISTANCE.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_unit() {

        double lat = 30;
        double lon = 50;
        int distance = 2;
        int page = 1;
        int size = 20;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getClosestStore(lat, lon, distance, null, page, size));

        assertEquals(ErrorMessages.INVALID_UNIT.getErrorMessage(), exception.getMessage());
    }

    @Test
    void should_throw_invalid_unit_value() {

        double lat = 30;
        double lon = 50;
        int distance = 2;
        String unit = "kmh";
        int page = 1;
        int size = 20;
        NyStoreException exception = assertThrows(NyStoreException.class,
                () -> storeService.getClosestStore(lat, lon, distance, unit, page, size));

        assertEquals(ErrorMessages.INVALID_UNIT_VALUE.getErrorMessage(), exception.getMessage());
    }
}