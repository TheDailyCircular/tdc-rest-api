package com.dailycircular.dailycircular.controller;

import com.dailycircular.dailycircular.model.Resume;
import com.dailycircular.dailycircular.service.ResumeServices;
import com.dailycircular.dailycircular.validation.ResumeValidator;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/user/resume")
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
