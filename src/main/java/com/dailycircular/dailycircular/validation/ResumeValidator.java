package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.Resume;
import com.dailycircular.dailycircular.repository.ResumeRepository;
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
