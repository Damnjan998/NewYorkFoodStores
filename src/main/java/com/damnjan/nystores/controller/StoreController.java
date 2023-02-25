package com.damnjan.nystores.controller;

import com.damnjan.nystores.exception.IncorrectConditionForNameOrAddressException;
import com.damnjan.nystores.exception.IncorrectLatitudeOrLongitudeException;
import com.damnjan.nystores.exception.InvalidPageOrSizeException;
import com.damnjan.nystores.model.responseModel.PageResponseModel;
import com.damnjan.nystores.model.responseModel.StoreDto;
import com.damnjan.nystores.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store/")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping()
    public ResponseEntity<Void> populateDbFromCsvFile() {
        storeService.populateES();
        return ResponseEntity.ok().build();
    }

    @GetMapping("geo-distance")
    public ResponseEntity<PageResponseModel<StoreDto>> getClosestStore(@RequestParam Double latitude,
                                          @RequestParam Double longitude,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size)
            throws IncorrectLatitudeOrLongitudeException, InvalidPageOrSizeException {
        return ResponseEntity.ok(storeService.getClosestStore(latitude, longitude, page, size));
    }

    @GetMapping("name_address")
    public ResponseEntity<PageResponseModel<StoreDto>> getStoreByNameOrAddress(@RequestParam String condition,
                                                                              @RequestParam(defaultValue = "1") Integer page,
                                                                              @RequestParam(defaultValue = "20") Integer size)
            throws IncorrectConditionForNameOrAddressException, InvalidPageOrSizeException {
        return ResponseEntity.ok(storeService.getStoreByNameOrAddress(condition, page, size));
    }
}
