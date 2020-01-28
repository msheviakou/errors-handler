package com.mekan.errors.handler.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
@Setter
@Builder
public class ApiError {

    private HttpStatus status;

    @Builder.Default
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

    private String message;
}
