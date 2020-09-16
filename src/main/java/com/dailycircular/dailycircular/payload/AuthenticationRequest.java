package com.dailycircular.dailycircular.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Email(message = "Provide a valid email")
    @NotBlank(message = "Email is required")
    protected String username;

    @NotBlank(message = "Password is required")
    protected String password;
}
