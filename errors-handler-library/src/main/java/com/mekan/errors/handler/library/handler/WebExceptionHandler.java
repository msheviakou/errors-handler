package com.mekan.errors.handler.library.handler;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.model.ApiErrorDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler implements ExceptionHandlerResponse {

    private final ExceptionHandlerConfig exceptionHandlerConfig;

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.valueOf((Integer) exceptionHandlerConfig.get(METHOD_ARGUMENT_NOT_VALID_MESSAGE));
        return buildResponseEntity(ApiErrorDetails.apiErrorDetailsBuilder()
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(hasText(e.getMessage()) ? e.getMessage() : String.format("%s", exceptionHandlerConfig.get(METHOD_ARGUMENT_NOT_VALID_STATUS)))
                .details(details)
                .build());
    }
}

