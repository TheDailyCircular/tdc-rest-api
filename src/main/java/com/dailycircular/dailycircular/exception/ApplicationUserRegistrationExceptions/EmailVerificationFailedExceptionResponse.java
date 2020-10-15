package com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailVerificationFailedExceptionResponse {
    private String token;
}
