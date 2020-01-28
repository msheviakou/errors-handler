package com.mekan.errors.handler.library.exception;

public class FileNotReadException extends RuntimeException {
    public FileNotReadException(Throwable cause) {
        super(cause);
    }

    public FileNotReadException(String message) {
        super(message);
    }

    public FileNotReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
