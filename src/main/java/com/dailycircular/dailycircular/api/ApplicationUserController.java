package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.payload.CategoryTagChoiceUpdateRequest;
import com.dailycircular.dailycircular.service.ApplicationUserServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserServices applicationUserServices;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    public ApplicationUserController(ApplicationUserServices applicationUserServices, ValidationErrorMappingServices validationErrorMappingServices) {
        this.applicationUserServices = applicationUserServices;
        this.validationErrorMappingServices = validationErrorMappingServices;
    }

    @PostMapping("/create/category-tag-choice")
    public ResponseEntity<?> saveOrUpdateCategoryAndTagChoice(
            @RequestBody @Valid CategoryTagChoiceUpdateRequest categoryTagChoiceUpdateRequest, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(categoryTagChoiceUpdateRequest, HttpStatus.CREATED);
    }

    @GetMapping("/circulars/{username}")
    public ResponseEntity<?> getCirculars(@PathVariable("username") String username) {
        return new ResponseEntity<>(applicationUserServices.getCirculars(username), HttpStatus.OK);
    }
}
