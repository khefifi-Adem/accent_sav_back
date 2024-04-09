package com.accent.sav.security.exception;

public class MustBeLowerThanException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long resourceId;

    public MustBeLowerThanException(Long resourceId, String message) {
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