package com.avis.fee.exception;

public class DataNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE= "Data not found";

    public DataNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
