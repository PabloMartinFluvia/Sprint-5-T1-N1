package com.martinfluviapablo.s5t1n1.model.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IdException extends IllegalArgumentException implements CustomException{

    private static final String DESCRIPTION = "Unprocessable Entity Exception (422)";

    private final String CAUSE;

    public IdException(String detail) {
        super(DESCRIPTION + ". " + detail);
        this.CAUSE = detail;
    }

    @Override
    public String getCAUSE() {
        return CAUSE;
    }
}
