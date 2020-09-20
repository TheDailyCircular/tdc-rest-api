package com.dailycircular.dailycircular.api;

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

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/user/register")
public class ApplicationUserRegistrationController {

    private final RegistrationRequestValidator registrationRequestValidator;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    private final ApplicationUserServices applicationUserServices;

    public ApplicationUserRegistrationController(
            RegistrationRequestValidator registrationRequestValidator,
            ValidationErrorMappingServices validationErrorMappingServices,
            ApplicationUserServices applicationUserServices) {

        this.registrationRequestValidator = registrationRequestValidator;
        this.validationErrorMappingServices = validationErrorMappingServices;
        this.applicationUserServices = applicationUserServices;
    }


    @PostMapping("/manual")
    public ResponseEntity<?> registerNewUser(
            @Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result) {

        registrationRequestValidator.validate(registrationRequest, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        ApplicationUser registeredApplicationUser =
                applicationUserServices.registerNewUser(registrationRequest.createApplicationUser());



        return new ResponseEntity<>(registeredApplicationUser, HttpStatus.CREATED);
    }


}
