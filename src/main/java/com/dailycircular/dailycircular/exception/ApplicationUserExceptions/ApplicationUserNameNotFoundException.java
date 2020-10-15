package com.dailycircular.dailycircular.exception.ApplicationUserExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationUserNameNotFoundException extends RuntimeException {

    public ApplicationUserNameNotFoundException(String message) {
        super(message);
    }
}
