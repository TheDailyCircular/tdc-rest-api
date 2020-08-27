package com.thedailycircular.tdc.validation;

import com.thedailycircular.tdc.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.thedailycircular.tdc.security.SecurityConstants.PASSWORD_MINIMUM_LENGTH;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;

        if (user.getPassword().length() < PASSWORD_MINIMUM_LENGTH) {
            errors.rejectValue("password", "Length",
                    "Password must be at least " + PASSWORD_MINIMUM_LENGTH + " characters");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");
        }
    }
}
