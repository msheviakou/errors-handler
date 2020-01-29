package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.model.ApiErrorDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Web.METHOD_ARGUMENT_NOT_VALID_MESSAGE;
import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Web.METHOD_ARGUMENT_NOT_VALID_STATUS;
import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler implements ExceptionHandlerResponse {

    private final ExceptionHandlerConfig exceptionHandlerConfig;

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        Integer httpStatus = (Integer) exceptionHandlerConfig.get(METHOD_ARGUMENT_NOT_VALID_STATUS);
        if (httpStatus != null) {
            status = HttpStatus.valueOf(httpStatus);
        }

        Object message = exceptionHandlerConfig.get(METHOD_ARGUMENT_NOT_VALID_MESSAGE);
        message = isEmpty(message) ? e.getMessage() : message;

        return buildResponseEntity(ApiErrorDetails.apiErrorDetailsBuilder()
                .status(status.value())
                .error(status.name())
                .message(String.format("%s", message))
                .details(details)
                .build());
    }
}

