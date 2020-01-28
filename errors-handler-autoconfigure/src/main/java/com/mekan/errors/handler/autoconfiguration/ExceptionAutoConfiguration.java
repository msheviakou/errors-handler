package com.mekan.errors.handler.autoconfiguration;

import com.mekan.errors.handler.library.config.ExceptionHandlerConfig;
import com.mekan.errors.handler.library.handler.RestCoreExceptionHandler;
import com.mekan.errors.handler.library.handler.RestCustomExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Core.*;
import static com.mekan.errors.handler.library.config.ExceptionHandlerConfigParams.Custom.*;
import static org.springframework.util.StringUtils.isEmpty;

@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(value = {RestCoreExceptionHandler.class, RestCustomExceptionHandler.class})
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
public class ExceptionAutoConfiguration {

    private final ExceptionHandlerProperties exceptionHandlerProperties;

    @Bean
    @ConditionalOnMissingBean
    public RestCoreExceptionHandler coreExceptionHandler(ExceptionHandlerConfig exceptionHandlerConfig) {
        return new RestCoreExceptionHandler(exceptionHandlerConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestCustomExceptionHandler customExceptionHandler(ExceptionHandlerConfig exceptionHandlerConfig) {
        return new RestCustomExceptionHandler(exceptionHandlerConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExceptionHandlerConfig exceptionHandlerConfig() {
        String noSuchElementMessage = isEmpty(exceptionHandlerProperties.getNoSuchElementMessage())  ? "No such element exception" : exceptionHandlerProperties.getNoSuchElementMessage();
        String illegalArgumentMessage = isEmpty(exceptionHandlerProperties.getIllegalArgumentMessage())? "Illegal argument exception" : exceptionHandlerProperties.getIllegalArgumentMessage();
        String fileNotReadMessage = isEmpty(exceptionHandlerProperties.getFileNotReadMessage())  ? "File not read exception" : exceptionHandlerProperties.getFileNotReadMessage();
        String fileStorageMessage = isEmpty(exceptionHandlerProperties.getFileStorageMessage())? "File storage exception" : exceptionHandlerProperties.getFileStorageMessage();
        String illegalAccessMessage = isEmpty(exceptionHandlerProperties.getIllegalAccessMessage())  ? "Illegal access exception" : exceptionHandlerProperties.getIllegalAccessMessage();
        String resourceNotFoundMessage = isEmpty(exceptionHandlerProperties.getResourceNotFoundMessage())? "Resource not found exception" : exceptionHandlerProperties.getResourceNotFoundMessage();

        Integer noSuchElementStatus = exceptionHandlerProperties.getNoSuchElementStatus() == null ? 404 : exceptionHandlerProperties.getNoSuchElementStatus();
        Integer illegalArgumentStatus = exceptionHandlerProperties.getIllegalArgumentStatus() == null ? 400 : exceptionHandlerProperties.getIllegalArgumentStatus();
        Integer fileNotReadStatus = exceptionHandlerProperties.getFileNotReadStatus() == null ? 404 : exceptionHandlerProperties.getFileNotReadStatus();
        Integer fileStorageStatus = exceptionHandlerProperties.getFileStorageStatus() == null ? 400 : exceptionHandlerProperties.getFileStorageStatus();
        Integer illegalAccessStatus = exceptionHandlerProperties.getIllegalAccessStatus() == null ? 404 : exceptionHandlerProperties.getIllegalAccessStatus();
        Integer resourceNotFoundStatus = exceptionHandlerProperties.getResourceNotFoundStatus() == null ? 400 : exceptionHandlerProperties.getResourceNotFoundStatus();

        ExceptionHandlerConfig exceptionHandlerConfig = new ExceptionHandlerConfig();
        exceptionHandlerConfig.put(NO_SUCH_ELEMENT_MESSAGE, noSuchElementMessage);
        exceptionHandlerConfig.put(ILLEGAL_ARGUMENT_MESSAGE, illegalArgumentMessage);
        exceptionHandlerConfig.put(FILE_NOT_READ_MESSAGE, fileNotReadMessage);
        exceptionHandlerConfig.put(FILE_STORAGE_MESSAGE, fileStorageMessage);
        exceptionHandlerConfig.put(ILLEGAL_ACCESS_MESSAGE, illegalAccessMessage);
        exceptionHandlerConfig.put(RESOURCE_NOT_FOUND_MESSAGE, resourceNotFoundMessage);

        exceptionHandlerConfig.put(NO_SUCH_ELEMENT_STATUS, noSuchElementStatus);
        exceptionHandlerConfig.put(ILLEGAL_ARGUMENT_STATUS, illegalArgumentStatus);
        exceptionHandlerConfig.put(FILE_NOT_READ_STATUS, fileNotReadStatus);
        exceptionHandlerConfig.put(FILE_STORAGE_STATUS, fileStorageStatus);
        exceptionHandlerConfig.put(ILLEGAL_ACCESS_STATUS, illegalAccessStatus);
        exceptionHandlerConfig.put(RESOURCE_NOT_FOUND_STATUS, resourceNotFoundStatus);

        return exceptionHandlerConfig;
    }

}
