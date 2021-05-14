package com.virtual.soft.axew.exception;

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 6831501108057106383L;

    public DatabaseException (String message) {
        super(message);
    }
}
