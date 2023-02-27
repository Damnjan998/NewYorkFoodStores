package com.damnjan.nystores.validator;

import co.elastic.clients.elasticsearch._types.DistanceUnit;
import com.damnjan.nystores.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class StoreValidatorService {

    private static final int MIN_LATITUDE = -90;
    private static final int MAX_LATITUDE = 90;
    private static final int MIN_LONGITUDE = -180;
    private static final int MAX_LONGITUDE = 180;

    public void checkLatitudeAndLongitude(double lat, double lon) {
        if (lat <= MIN_LATITUDE || lat >= MAX_LATITUDE || lon <= MIN_LONGITUDE || lon >= MAX_LONGITUDE) {
            throw ExceptionSupplier.invalidLatOrLon.get();
        }
    }

    public void checkNameOrAddress(String condition) {
        if (!StringUtils.hasText(condition)) {
            throw ExceptionSupplier.invalidConditionForNameOrAddress.get();
        }
    }

    public void checkPageOrSize(int page, int size) {
        if (page <= 0) {
            throw ExceptionSupplier.invalidPageNumber.get();
        }

        if (size < 1) {
            throw ExceptionSupplier.invalidSizeNumber.get();        }
    }

    public void checkDistanceAndUnit(int distance, String unit) {
        if (distance < 1) {
            throw ExceptionSupplier.invalidDistance.get();
        }

        if (!StringUtils.hasText(unit)) {
            throw ExceptionSupplier.invalidUnit.get();
        } else {
            DistanceUnit[] values = DistanceUnit.values();

            if (Arrays.stream(values).noneMatch(distanceUnit -> distanceUnit.jsonValue().equals(unit))) {
                throw ExceptionSupplier.invalidUnitValue.get();
            }
        }
    }
}
