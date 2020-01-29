package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.exception.FileNotReadException;
import com.mekan.errors.handler.library.exception.FileStorageException;
import com.mekan.errors.handler.library.exception.IllegalAccessException;
import com.mekan.errors.handler.library.exception.ResourceNotFoundException;
import com.mekan.errors.handler.library.model.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Custom.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler implements ExceptionHandlerResponse {

    private final ExceptionHandlerConfig exceptionHandlerConfig;

    @ExceptionHandler(FileNotReadException.class)
    public ResponseEntity handle(FileNotReadException e) {
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(FILE_NOT_READ_STATUS));
        return buildResponseEntity(ApiError.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(FILE_NOT_READ_MESSAGE)))
                .build());
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity handle(FileStorageException e) {
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(FILE_STORAGE_STATUS));
        return buildResponseEntity(ApiError.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(FILE_STORAGE_MESSAGE)))
                .build());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity handle(IllegalAccessException e) {
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(ILLEGAL_ACCESS_STATUS));
        return buildResponseEntity(ApiError.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(ILLEGAL_ACCESS_MESSAGE)))
                .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handle(ResourceNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(RESOURCE_NOT_FOUND_STATUS));
        return buildResponseEntity(ApiError.builder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(RESOURCE_NOT_FOUND_MESSAGE)))
                .build());
    }
}
