package com.thedailycircular.tdc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEmailAlreadyRegisteredExceptionResponse {
    private String username;
}
