package com.martinfluviapablo.s5t1n1.model.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SucursalNotFoundException extends RuntimeException implements CustomException{

    private static final String DESCRIPTION = "Not Found Exception (404)";

    private final String detail;

    public SucursalNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
        this.detail = detail;
    }

    @Override
    public String getDetail(){
        return detail;
    }
}
