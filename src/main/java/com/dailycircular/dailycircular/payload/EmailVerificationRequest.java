package com.dailycircular.dailycircular.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.dailycircular.dailycircular.constants.SecurityConstants.EMAIL_CONFIRMATION_TOKEN_LENGTH;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationRequest {

    @Email(message = "Provide a valid email")
    @NotBlank(message = "Email is required")
    private String username;

    @NotBlank(message = "Token is required")
    @Size(min = EMAIL_CONFIRMATION_TOKEN_LENGTH, max = EMAIL_CONFIRMATION_TOKEN_LENGTH)
    private String token;
}
