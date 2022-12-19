package com.avis.fee.exception;

public class BadRequestException extends RuntimeException{
    private static final String ERROR_MESSAGE= "Bad Request";

    public BadRequestException() {
        super(ERROR_MESSAGE);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable t) {
        super(message, t);
    }
}
