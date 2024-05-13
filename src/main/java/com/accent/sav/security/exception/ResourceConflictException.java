package com.accent.sav.security.exception;

public class ResourceConflictException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResourceConflictException(String exception) {
        super(exception);
    }
}
