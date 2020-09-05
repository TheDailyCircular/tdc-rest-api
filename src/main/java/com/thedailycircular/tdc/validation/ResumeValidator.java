package com.thedailycircular.tdc.validation;

import com.thedailycircular.tdc.model.Resume;
import com.thedailycircular.tdc.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResumeValidator implements Validator {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Resume.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

    }
}
