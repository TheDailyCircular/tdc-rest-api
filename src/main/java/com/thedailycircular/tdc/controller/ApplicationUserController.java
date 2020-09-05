package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.event.OnRegistrationCompleteEvent;
import com.thedailycircular.tdc.model.ApplicationUser;
import com.thedailycircular.tdc.payload.RegistrationRequest;
import com.thedailycircular.tdc.service.ApplicationUserServices;
import com.thedailycircular.tdc.validation.RegistrationRequestValidator;
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
public class ApplicationUserController {

    private final ApplicationUserServices applicationUserServices;

    private final RegistrationRequestValidator registrationRequestValidator;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    @Autowired
    public ApplicationUserController(ApplicationUserServices applicationUserServices,
                                     RegistrationRequestValidator registrationRequestValidator,
                                     ApplicationEventPublisher applicationEventPublisher,
                                     ValidationErrorMappingServices validationErrorMappingServices) {

        this.applicationUserServices = applicationUserServices;
        this.registrationRequestValidator = registrationRequestValidator;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validationErrorMappingServices = validationErrorMappingServices;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(
            @Valid @RequestBody RegistrationRequest registrationRequest,
            BindingResult result, HttpServletRequest request) {

        registrationRequestValidator.validate(registrationRequest, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        ApplicationUser registeredApplicationUser =
                applicationUserServices.registerNewUser(registrationRequest.createUser());
        String appUrl = request.getContextPath();
        applicationEventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(
                        registeredApplicationUser, request.getLocale(), appUrl
                )
        );
        return new ResponseEntity<>(registeredApplicationUser, HttpStatus.CREATED);
    }

    @GetMapping("/circulars/{username}")
    public ResponseEntity<?> getCirculars(@PathVariable("username") String username) {
        return new ResponseEntity<>(applicationUserServices.getCirculars(username), HttpStatus.OK);
    }
}
