package com.thedailycircular.tdc.validation;

import com.thedailycircular.tdc.model.Resume;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResumeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Resume.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {


    }
}
