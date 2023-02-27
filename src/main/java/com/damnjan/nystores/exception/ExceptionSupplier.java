package com.damnjan.nystores.exception;

import com.damnjan.nystores.util.ErrorMessages;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class ExceptionSupplier {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final Supplier<NyStoreException> invalidUnitValue = () -> new NyStoreException(
            ErrorMessages.INVALID_UNIT_VALUE.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );

    public static final Supplier<NyStoreException> invalidUnit = () -> new NyStoreException(
            ErrorMessages.INVALID_UNIT.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
    public static final Supplier<NyStoreException> invalidDistance = () -> new NyStoreException(
            ErrorMessages.INVALID_DISTANCE.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
    public static final Supplier<NyStoreException> invalidPageNumber = () -> new NyStoreException(
            ErrorMessages.PAGE_NUMBER_WRONG.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
    public static final Supplier<NyStoreException> invalidSizeNumber = () -> new NyStoreException(
            ErrorMessages.SIZE_NUMBER_WRONG.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
    public static final Supplier<NyStoreException> invalidConditionForNameOrAddress = () -> new NyStoreException(
            ErrorMessages.INVALID_CONDITION_FOR_NAME_OR_ADDRESS.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
    public static final Supplier<NyStoreException> invalidLatOrLon = () -> new NyStoreException(
            ErrorMessages.INVALID_LAT_LON.getErrorMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now().format(formatter),
            ErrorMessages.BAD_REQUEST.getErrorMessage()
    );
}
