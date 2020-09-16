package com.dailycircular.dailycircular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityIdNotFoundException extends RuntimeException {

    public EntityIdNotFoundException(String message) {
        super(message);
    }
}
