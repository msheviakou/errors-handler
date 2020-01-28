package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.model.ApiError;
import org.springframework.http.ResponseEntity;

abstract class RestExceptionHandler {

    ResponseEntity buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
