package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.EducationalQualification;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EducationalQualificationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return EducationalQualification.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        EducationalQualification educationalQualification = (EducationalQualification) object;

        if (educationalQualification.getDegree() == null ||
                educationalQualification.getDegree().equals("")) {
            errors.rejectValue("degree", "blank", "Degree can not be empty");
        }

        if (educationalQualification.getInstitute() == null ||
                educationalQualification.getInstitute().equals("")) {
            errors.rejectValue("institute", "blank", "Institute can not be empty");
        }
    }
}
