package com.dailycircular.dailycircular.controller;

import com.dailycircular.dailycircular.event.OnRegistrationCompleteEvent;
import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.payload.RegistrationRequest;
import com.dailycircular.dailycircular.service.ApplicationUserServices;
import com.dailycircular.dailycircular.validation.RegistrationRequestValidator;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/user/register")
public class ApplicationUserRegistrationController {

    private final RegistrationRequestValidator registrationRequestValidator;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    private final ApplicationUserServices applicationUserServices;

    public ApplicationUserRegistrationController(
            RegistrationRequestValidator registrationRequestValidator,
            ApplicationEventPublisher applicationEventPublisher,
            ValidationErrorMappingServices validationErrorMappingServices,
            ApplicationUserServices applicationUserServices) {

        this.registrationRequestValidator = registrationRequestValidator;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validationErrorMappingServices = validationErrorMappingServices;
        this.applicationUserServices = applicationUserServices;
    }


    @PostMapping("/manual")
    public ResponseEntity<?> registerNewUser(
            @Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result, HttpServletRequest request) {

        registrationRequestValidator.validate(registrationRequest, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        ApplicationUser registeredApplicationUser =
                applicationUserServices.registerNewUser(registrationRequest.createApplicationUser());
        String appUrl = request.getContextPath();
        applicationEventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(
                        registeredApplicationUser, request.getLocale(), appUrl
                )
        );
        return new ResponseEntity<>(registeredApplicationUser, HttpStatus.CREATED);
    }


}
