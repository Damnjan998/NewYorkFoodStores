package com.damnjan.nystores.util;

public enum ErrorMessages {

    INVALID_UNIT_VALUE("Bad unit value."),
    BAD_REQUEST("Bad request."),
    INVALID_UNIT("Unit must be present."),
    INVALID_DISTANCE("Distance must be greater then 0."),
    PAGE_NUMBER_WRONG("Page index must not be less than one."),
    SIZE_NUMBER_WRONG("Page size must not be less than one."),
    INVALID_CONDITION_FOR_NAME_OR_ADDRESS("You must provide condition for search."),
    INVALID_LAT_LON("Latitude or Longitude are out of range.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
