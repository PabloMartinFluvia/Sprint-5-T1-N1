package com.martinfluviapablo.s5t1n1.model.services.exceptions;

public class DataConflictException extends RuntimeException{

    private static final String DESCRIPTION = "Conflict Exception (409)";

    private final String CAUSE;

    public DataConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
        this.CAUSE = detail;
    }

    public String getCAUSE() {
        return CAUSE;
    }
}
