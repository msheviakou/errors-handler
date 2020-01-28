package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.model.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Core.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@ControllerAdvice
public class RestCoreExceptionHandler extends RestExceptionHandler {

    private final ExceptionHandlerConfig exceptionHandlerConfig;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handle(NoSuchElementException e) {
        return buildResponseEntity(ApiError.builder()
                .status(HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(NO_SUCH_ELEMENT_STATUS)))
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(NO_SUCH_ELEMENT_MESSAGE)))
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handle(IllegalArgumentException e) {
        return buildResponseEntity(ApiError.builder()
                .status(HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(ILLEGAL_ARGUMENT_STATUS)))
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(ILLEGAL_ARGUMENT_MESSAGE)))
                .build());
    }
}
