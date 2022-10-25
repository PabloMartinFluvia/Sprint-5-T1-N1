package com.martinfluviapablo.s5t1n1.model.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataConflictException extends RuntimeException implements CustomException{

    private static final String DESCRIPTION = "Conflict Exception (409)";

    private final String detail;

    public DataConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
        this.detail = detail;
    }

    @Override
    public String getDetail(){
        return detail;
    }
}
