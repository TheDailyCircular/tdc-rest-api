package com.dailycircular.dailycircular.exception.ApplicationUserExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationUserNameNotFoundExceptionResponse {
    private String username;
}
