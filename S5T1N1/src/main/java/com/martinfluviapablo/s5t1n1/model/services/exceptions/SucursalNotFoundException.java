package com.martinfluviapablo.s5t1n1.model.services.exceptions;

public class SucursalNotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "Not Found Exception (404)";

    private final String CAUSE;

    public SucursalNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
        this.CAUSE = detail;
    }

    public String getCAUSE() {
        return CAUSE;
    }
}
