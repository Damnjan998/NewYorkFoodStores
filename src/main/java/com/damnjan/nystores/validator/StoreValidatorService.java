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

    public void checkLatitudeAndLongitude(Double latitude, Double longitude) throws IncorrectLatitudeOrLongitudeException {
        if (latitude <= MIN_LATITUDE || latitude >= MAX_LATITUDE || longitude <= MIN_LONGITUDE || longitude >= MAX_LONGITUDE) {
            throw new IncorrectLatitudeOrLongitudeException("Latitude or Longitude are out of range.");
        }
    }

    public void checkNameOrAddress(String condition) throws IncorrectConditionForNameOrAddressException {
        if (!StringUtils.hasText(condition)) {
            throw new IncorrectConditionForNameOrAddressException("You must provide condition for search.");
        }
    }

    public void checkPageOrSize(Integer page, Integer size) throws InvalidPageOrSizeException {
        if (page <= 0) {
            throw new InvalidPageOrSizeException("Page index must not be less than one.");
        }

        if (size < 1) {
            throw new InvalidPageOrSizeException("Page size must not be less than one.");
        }
    }

    public void checkDistanceAndUnit(int distance, String unit) throws InvalidDistanceException,
            InvalidUnitException, InvalidUnitValueException {
        if (distance < 1) {
            throw new InvalidDistanceException("Distance must be greater then 0.");
        }

        if (!StringUtils.hasText(unit)) {
            throw new InvalidUnitException("Unit must be present.");
        } else {
            DistanceUnit[] values = DistanceUnit.values();

            if (Arrays.stream(values).noneMatch(distanceUnit -> distanceUnit.jsonValue().equals(unit))) {
                throw new InvalidUnitValueException("Bad unit value.");
            }
        }
    }
}
