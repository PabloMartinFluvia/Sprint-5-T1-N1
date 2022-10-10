package com.martinfluviapablo.s5t1n1.model.services;

public class DataConflictiveException extends RuntimeException{

    private static final String DESCRIPTION = "Conflict Exception (409)";

    public DataConflictiveException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
