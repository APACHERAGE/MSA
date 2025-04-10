package com.demo.service.exception;

public class NonExistingEmailException extends RuntimeException {
    public NonExistingEmailException(String message) {
        super(message);
    }
}
