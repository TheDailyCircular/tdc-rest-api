package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.model.EducationalQualification;
import com.thedailycircular.tdc.model.Resume;
import com.thedailycircular.tdc.service.ResumeServices;
import com.thedailycircular.tdc.validation.EducationalQualificationValidator;
import com.thedailycircular.tdc.validation.ResumeValidator;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user/resume")
public class ResumeController {

    @Autowired
    private ResumeServices resumeServices;

    @Autowired
    private ResumeValidator resumeValidator;

    @Autowired
    private EducationalQualificationValidator educationalQualificationValidator;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Resume resume, BindingResult result) {
        resumeValidator.validate(resume, result);
        for (EducationalQualification educationalQualification :
                resume.getEducationalQualifications()) {
            educationalQualificationValidator.validate(educationalQualification, result);
        }

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(resumeServices.save(resume), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resumeServices.get(id), HttpStatus.OK);
    }
}
