package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.event.OnRegistrationCompleteEvent;
import com.thedailycircular.tdc.model.User;
import com.thedailycircular.tdc.service.UserServices;
import com.thedailycircular.tdc.validation.UserValidator;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(
            @Valid @RequestBody User user, BindingResult result, HttpServletRequest request) {

        userValidator.validate(user, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) {
            return errorMap;
        }
        User registeredUser = userServices.registerNewUser(user);
        String appUrl = request.getContextPath();
        applicationEventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(
                        registeredUser, request.getLocale(), appUrl
                )
        );
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/circulars/{username}")
    public ResponseEntity<?> getCirculars(@PathVariable("username") String username) {
        return new ResponseEntity<>(userServices.getCirculars(username), HttpStatus.OK);
    }
}
