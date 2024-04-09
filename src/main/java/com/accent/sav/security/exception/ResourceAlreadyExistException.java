package com.accent.sav.security.exception;

public class ResourceAlreadyExistException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long resourceId;

    public ResourceAlreadyExistException(String message) {
        super(message);
    }

    public ResourceAlreadyExistException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

}