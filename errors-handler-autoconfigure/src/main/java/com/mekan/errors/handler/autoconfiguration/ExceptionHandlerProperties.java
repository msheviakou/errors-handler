package com.mekan.errors.handler.autoconfiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "exception.handle")
public class ExceptionHandlerProperties {
    private String noSuchElementMessage;
    private String illegalArgumentMessage;
    private String fileNotReadMessage;
    private String fileStorageMessage;
    private String illegalAccessMessage;
    private String resourceNotFoundMessage;
    private String methodArgumentNotValidMessage;

    private Integer noSuchElementStatus;
    private Integer illegalArgumentStatus;
    private Integer fileNotReadStatus;
    private Integer fileStorageStatus;
    private Integer illegalAccessStatus;
    private Integer resourceNotFoundStatus;
    private Integer methodArgumentNotValidStatus;
}
