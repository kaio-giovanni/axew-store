package com.virtual.soft.axew.exception;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 4458369033210309068L;

    public AuthorizationException(String message) {
        super(message);
    }
}
