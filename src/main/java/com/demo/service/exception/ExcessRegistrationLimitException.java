package com.demo.service.exception;

public class ExcessRegistrationLimitException extends RuntimeException {
    public ExcessRegistrationLimitException(String message) {
        super(message);
    }
}
