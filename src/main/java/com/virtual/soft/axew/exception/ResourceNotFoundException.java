package com.virtual.soft.axew.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5105399317582997672L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
