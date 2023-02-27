package com.damnjan.nystores.exception;

import com.damnjan.nystores.model.responseModel.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NyStoreExceptionHandler {

    @ExceptionHandler(value = {NyStoreException.class})
    public ResponseEntity<Object> handleBadRequestException(NyStoreException ex, WebRequest webRequest) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorMessage(
                        ex.getMessage(), ex.getStatus(), ex.getTimestamp(), ex.getErrorMessage()
                ));
    }
}
