package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.model.jwt.AuthenticationRequest;
import com.thedailycircular.tdc.model.jwt.AuthenticationResponse;
import com.thedailycircular.tdc.security.JWTUtility;
import com.thedailycircular.tdc.service.UserServices;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return validationErrorMappingServices.invalidEmailPasswordForLogin();
        }

        final UserDetails userDetails = userServices.loadUserByUsername(authenticationRequest.getUsername());

        final String jwtToken = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
