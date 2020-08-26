package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.payload.AuthenticationRequest;
import com.thedailycircular.tdc.payload.AuthenticationResponse;
import com.thedailycircular.tdc.security.JWTUtility;
import com.thedailycircular.tdc.service.UserServices;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServices userServices;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @Valid @RequestBody AuthenticationRequest authenticationRequest,
            BindingResult result) throws Exception {

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
