package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.payload.AuthenticationRequest;
import com.dailycircular.dailycircular.payload.AuthenticationResponse;
import com.dailycircular.dailycircular.security.JWTUtility;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dailycircular.dailycircular.constants.SecurityConstants.JWT_TOKEN_PREFIX;


@CrossOrigin
@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JWTUtility jwtUtility;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JWTUtility jwtUtility,
            ValidationErrorMappingServices validationErrorMappingServices) {

        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
        this.validationErrorMappingServices = validationErrorMappingServices;
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @Valid @RequestBody AuthenticationRequest authenticationRequest,
            BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) {
            return errorMap;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwtToken = JWT_TOKEN_PREFIX + jwtUtility.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponse(true, jwtToken));
    }
}
