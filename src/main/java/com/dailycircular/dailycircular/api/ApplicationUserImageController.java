package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.ApplicationUserImage;
import com.dailycircular.dailycircular.service.ApplicationUserImageServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/user_image")
public class ApplicationUserImageController {
    @Autowired
    private ApplicationUserImageServices applicationUserImageServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody ApplicationUserImage applicationUserImage, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if( errorMap != null ) {
            return  errorMap;
        }
        return new ResponseEntity<>(applicationUserImageServices.saveOrUpdate(applicationUserImage), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(applicationUserImageServices.getById(id), HttpStatus.OK);
    }
}
