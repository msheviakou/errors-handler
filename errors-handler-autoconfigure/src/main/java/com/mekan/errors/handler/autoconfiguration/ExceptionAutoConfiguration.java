package com.mekan.errors.handler.autoconfiguration;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.handler.CoreExceptionHandler;
import com.mekan.errors.handler.library.handler.CustomExceptionHandler;
import com.mekan.errors.handler.library.handler.WebExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Core.*;
import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Custom.*;
import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Web.METHOD_ARGUMENT_NOT_VALID_MESSAGE;
import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Web.METHOD_ARGUMENT_NOT_VALID_STATUS;
import static org.springframework.util.StringUtils.isEmpty;

@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(value = {CoreExceptionHandler.class, CustomExceptionHandler.class, WebExceptionHandler.class})
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
public class ExceptionAutoConfiguration {

    private final ExceptionHandlerProperties exceptionHandlerProperties;

    @Bean
    @ConditionalOnMissingBean
    public CoreExceptionHandler coreExceptionHandler(ExceptionHandlerConfig exceptionHandlerConfig) {
        return new CoreExceptionHandler(exceptionHandlerConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomExceptionHandler customExceptionHandler(ExceptionHandlerConfig exceptionHandlerConfig) {
        return new CustomExceptionHandler(exceptionHandlerConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebExceptionHandler webExceptionHandler(ExceptionHandlerConfig exceptionHandlerConfig) {
        return new WebExceptionHandler(exceptionHandlerConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandlerConfig exceptionHandlerConfig() {
        ExceptionHandlerConfig exceptionHandlerConfig = new ExceptionHandlerConfig();
        fillCoreHandlerConfig(exceptionHandlerConfig);
        fillCustomHandlerConfig(exceptionHandlerConfig);
        fillWebHandlerConfig(exceptionHandlerConfig);
        return exceptionHandlerConfig;
    }

    private void fillCoreHandlerConfig(ExceptionHandlerConfig exceptionHandlerConfig) {
        String noSuchElementMessage = isEmpty(exceptionHandlerProperties.getNoSuchElementMessage())  ? "No such element exception" : exceptionHandlerProperties.getNoSuchElementMessage();
        String illegalArgumentMessage = isEmpty(exceptionHandlerProperties.getIllegalArgumentMessage())? "Illegal argument exception" : exceptionHandlerProperties.getIllegalArgumentMessage();

        Integer noSuchElementStatus = exceptionHandlerProperties.getNoSuchElementStatus() == null ? 404 : exceptionHandlerProperties.getNoSuchElementStatus();
        Integer illegalArgumentStatus = exceptionHandlerProperties.getIllegalArgumentStatus() == null ? 400 : exceptionHandlerProperties.getIllegalArgumentStatus();

        exceptionHandlerConfig.put(NO_SUCH_ELEMENT_MESSAGE, noSuchElementMessage);
        exceptionHandlerConfig.put(ILLEGAL_ARGUMENT_MESSAGE, illegalArgumentMessage);

        exceptionHandlerConfig.put(NO_SUCH_ELEMENT_STATUS, noSuchElementStatus);
        exceptionHandlerConfig.put(ILLEGAL_ARGUMENT_STATUS, illegalArgumentStatus);
    }

    private void fillCustomHandlerConfig(ExceptionHandlerConfig exceptionHandlerConfig) {
        String fileNotReadMessage = isEmpty(exceptionHandlerProperties.getFileNotReadMessage())  ? "File not read exception" : exceptionHandlerProperties.getFileNotReadMessage();
        String fileStorageMessage = isEmpty(exceptionHandlerProperties.getFileStorageMessage())? "File storage exception" : exceptionHandlerProperties.getFileStorageMessage();
        String illegalAccessMessage = isEmpty(exceptionHandlerProperties.getIllegalAccessMessage())  ? "Illegal access exception" : exceptionHandlerProperties.getIllegalAccessMessage();
        String resourceNotFoundMessage = isEmpty(exceptionHandlerProperties.getResourceNotFoundMessage())? "Resource not found exception" : exceptionHandlerProperties.getResourceNotFoundMessage();

        Integer fileNotReadStatus = exceptionHandlerProperties.getFileNotReadStatus() == null ? 404 : exceptionHandlerProperties.getFileNotReadStatus();
        Integer fileStorageStatus = exceptionHandlerProperties.getFileStorageStatus() == null ? 400 : exceptionHandlerProperties.getFileStorageStatus();
        Integer illegalAccessStatus = exceptionHandlerProperties.getIllegalAccessStatus() == null ? 404 : exceptionHandlerProperties.getIllegalAccessStatus();
        Integer resourceNotFoundStatus = exceptionHandlerProperties.getResourceNotFoundStatus() == null ? 400 : exceptionHandlerProperties.getResourceNotFoundStatus();

        exceptionHandlerConfig.put(FILE_NOT_READ_MESSAGE, fileNotReadMessage);
        exceptionHandlerConfig.put(FILE_STORAGE_MESSAGE, fileStorageMessage);
        exceptionHandlerConfig.put(ILLEGAL_ACCESS_MESSAGE, illegalAccessMessage);
        exceptionHandlerConfig.put(RESOURCE_NOT_FOUND_MESSAGE, resourceNotFoundMessage);

        exceptionHandlerConfig.put(FILE_NOT_READ_STATUS, fileNotReadStatus);
        exceptionHandlerConfig.put(FILE_STORAGE_STATUS, fileStorageStatus);
        exceptionHandlerConfig.put(ILLEGAL_ACCESS_STATUS, illegalAccessStatus);
        exceptionHandlerConfig.put(RESOURCE_NOT_FOUND_STATUS, resourceNotFoundStatus);
    }

    private void fillWebHandlerConfig(ExceptionHandlerConfig exceptionHandlerConfig) {
        String methodArgumentNotValidMessage = isEmpty(exceptionHandlerProperties.getMethodArgumentNotValidMessage())? "Method argument not valid exception" : exceptionHandlerProperties.getMethodArgumentNotValidMessage();
        Integer methodArgumentNotValidStatus = exceptionHandlerProperties.getMethodArgumentNotValidStatus() == null ? 400 : exceptionHandlerProperties.getMethodArgumentNotValidStatus();

        exceptionHandlerConfig.put(METHOD_ARGUMENT_NOT_VALID_MESSAGE, methodArgumentNotValidMessage);
        exceptionHandlerConfig.put(METHOD_ARGUMENT_NOT_VALID_STATUS, methodArgumentNotValidStatus);
    }
}
