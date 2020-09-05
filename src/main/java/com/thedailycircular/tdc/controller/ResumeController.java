package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.model.Resume;
import com.thedailycircular.tdc.service.ResumeServices;
import com.thedailycircular.tdc.validation.ResumeValidator;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user/resume")
public class ResumeController {

    private final ResumeServices resumeServices;

    private final ResumeValidator resumeValidator;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    public ResumeController(ResumeServices resumeServices,
                            ResumeValidator resumeValidator,
                            ValidationErrorMappingServices validationErrorMappingServices) {

        this.resumeServices = resumeServices;
        this.resumeValidator = resumeValidator;
        this.validationErrorMappingServices = validationErrorMappingServices;
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Resume resume, BindingResult result) {
        resumeValidator.validate(resume, result);

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(resumeServices.update(resume), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(resumeServices.get(id), HttpStatus.OK);
    }
}
