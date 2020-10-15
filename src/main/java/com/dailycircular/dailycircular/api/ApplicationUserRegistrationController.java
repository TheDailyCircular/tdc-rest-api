package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.mail.MailServices;
import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.EmailVerificationToken;
import com.dailycircular.dailycircular.payload.EmailVerificationRequest;
import com.dailycircular.dailycircular.payload.RegistrationRequest;
import com.dailycircular.dailycircular.service.ApplicationUserRegistrationServices;
import com.dailycircular.dailycircular.validation.RegistrationRequestValidator;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
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

    private final ApplicationUserRegistrationServices applicationUserRegistrationServices;

    private final MailServices mailServices;

    public ApplicationUserRegistrationController(
            RegistrationRequestValidator registrationRequestValidator,
            ValidationErrorMappingServices validationErrorMappingServices,
            ApplicationUserRegistrationServices applicationUserRegistrationServices,
            MailServices mailServices) {

        this.registrationRequestValidator = registrationRequestValidator;
        this.validationErrorMappingServices = validationErrorMappingServices;
        this.applicationUserRegistrationServices = applicationUserRegistrationServices;
        this.mailServices = mailServices;
    }


    @PostMapping("/manual")
    public ResponseEntity<?> registerNewUser(
            @Valid @RequestBody RegistrationRequest registrationRequest, BindingResult result) {

        registrationRequestValidator.validate(registrationRequest, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        ApplicationUser registeredApplicationUser =
                applicationUserRegistrationServices.registerNewUser(registrationRequest.createApplicationUser());
        EmailVerificationToken emailVerificationToken =
                applicationUserRegistrationServices.createEmailVerificationToken(registeredApplicationUser);

        mailServices.sendEmailVerificationTokenMail(registeredApplicationUser, emailVerificationToken);

        return new ResponseEntity<>(registeredApplicationUser, HttpStatus.CREATED);
    }

    @PostMapping("/confirm_registration")
    public ResponseEntity<?> verifyEmail(
            @Valid @RequestBody EmailVerificationRequest emailVerificationRequest, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(applicationUserRegistrationServices.verifyEmail(emailVerificationRequest), HttpStatus.ACCEPTED);
    }
}
