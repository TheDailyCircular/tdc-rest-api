package com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailVerificationFailedException extends RuntimeException {

    public EmailVerificationFailedException(String message) {
        super(message);
    }
}
