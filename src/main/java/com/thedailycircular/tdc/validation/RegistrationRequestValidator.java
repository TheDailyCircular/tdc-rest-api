package com.thedailycircular.tdc.validation;

import com.thedailycircular.tdc.payload.RegistrationRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.thedailycircular.tdc.security.SecurityConstants.PASSWORD_MAXIMUM_LENGTH;
import static com.thedailycircular.tdc.security.SecurityConstants.PASSWORD_MINIMUM_LENGTH;

@Component
public class RegistrationRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        RegistrationRequest registrationRequest = (RegistrationRequest) object;

        if (registrationRequest.getPassword() == null ||
                registrationRequest.getPassword().length() < PASSWORD_MINIMUM_LENGTH) {
            errors.rejectValue("password", "Length",
                    "Password must be at least " + PASSWORD_MINIMUM_LENGTH + " characters");
        }

        if (registrationRequest.getPassword().length() > PASSWORD_MAXIMUM_LENGTH) {
            errors.rejectValue("password", "Length",
                    "Password must be at max " + PASSWORD_MAXIMUM_LENGTH + " characters");
        }

        if (registrationRequest.getConfirmPassword() == null ||
                !registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");
        }
    }
}
