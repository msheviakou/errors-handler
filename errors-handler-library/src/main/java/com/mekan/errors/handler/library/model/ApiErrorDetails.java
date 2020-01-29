package com.mekan.errors.handler.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiErrorDetails extends ApiError {

    private final List<String> details;

    @Builder(builderMethodName = "apiErrorDetailsBuilder")
    public ApiErrorDetails(Integer status, String error, String message, LocalDateTime timeStamp, List<String> details) {
        super(status, error, message, timeStamp);
        this.details = details;
    }
}
