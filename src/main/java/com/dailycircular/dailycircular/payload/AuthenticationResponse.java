package com.dailycircular.dailycircular.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private boolean success;
    private final String jwtToken;

    public boolean isSuccess() {
        return success;
    }
}
