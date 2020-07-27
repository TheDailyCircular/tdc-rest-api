package com.thedailycircular.tdc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserEmailAlreadyRegisteredException extends RuntimeException {

    public UserEmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
