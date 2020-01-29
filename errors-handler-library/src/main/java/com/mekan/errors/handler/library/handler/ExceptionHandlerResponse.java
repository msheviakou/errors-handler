package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.model.ApiError;
import com.mekan.errors.handler.library.model.ApiErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

interface ExceptionHandlerResponse {

    default ResponseEntity buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    default ResponseEntity buildResponseEntity(ApiErrorDetails apiErrorDetails) {
        return new ResponseEntity<>(apiErrorDetails, HttpStatus.valueOf(apiErrorDetails.getStatus()));
    }
}
