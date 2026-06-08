package com.smartlogix.inventory.domain.exception;

public class InvalidSkuException extends RuntimeException {
    public InvalidSkuException(String message) {
        super(message);
    }
}
