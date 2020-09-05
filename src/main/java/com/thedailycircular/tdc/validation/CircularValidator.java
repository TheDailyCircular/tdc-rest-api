package com.thedailycircular.tdc.validation;

import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.model.ApplicationUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CircularValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Circular.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Circular circular = (Circular) o;

        ApplicationUser applicationUser = circular.getApplicationUser();
        if (applicationUser.getId() == null) {
            errors.rejectValue("user", "user", "user is invalid");
        }
    }
}
