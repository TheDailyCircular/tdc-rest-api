package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.payload.AuthenticationRequest;
import com.thedailycircular.tdc.payload.AuthenticationResponse;
import com.thedailycircular.tdc.security.JWTUtility;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.thedailycircular.tdc.security.SecurityConstants.JWT_TOKEN_PREFIX;

@CrossOrigin
@RestController
@RequestMapping(path = "api/auth")
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
