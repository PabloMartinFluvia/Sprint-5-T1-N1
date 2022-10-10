package com.martinfluviapablo.s5t1n1.model.services;

public class SucursalNotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public SucursalNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
