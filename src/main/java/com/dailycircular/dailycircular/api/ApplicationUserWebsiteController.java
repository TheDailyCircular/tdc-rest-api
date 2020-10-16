package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.ApplicationUserWebsite;
import com.dailycircular.dailycircular.service.ApplicationUserWebsiteServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/user_website")
public class ApplicationUserWebsiteController {
    @Autowired
    private ApplicationUserWebsiteServices applicationUserWebsiteServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody ApplicationUserWebsite applicationUserWebsite, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if( errorMap != null ) {
            return  errorMap;
        }
        return new ResponseEntity<>(applicationUserWebsiteServices.saveOrUpdate(applicationUserWebsite), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(applicationUserWebsiteServices.getById(id), HttpStatus.OK);
    }
}
