package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.service.CircularCategoryServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/circular_category")
public class CircularCategoryController {
    @Autowired
    private CircularCategoryServices circularCategoryServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody CircularCategory circularCategory, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if( errorMap != null ) {
            return errorMap;
        }
        return new ResponseEntity<>(circularCategoryServices.saveOrUpdate(circularCategory), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(circularCategoryServices.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public Page<CircularCategory> getAll(@RequestParam Optional<Integer> page,
                                         @RequestParam Optional<Integer> size) {
        return circularCategoryServices.getAll(page.orElse(0), size.orElse(5));
    }
}
