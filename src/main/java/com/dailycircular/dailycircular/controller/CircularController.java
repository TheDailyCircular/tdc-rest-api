package com.dailycircular.dailycircular.controller;

import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.service.CircularServices;
import com.dailycircular.dailycircular.validation.CircularValidator;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/circular")
public class CircularController {

    private final CircularServices circularServices;

    private final CircularValidator circularValidator;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    public CircularController(
            CircularServices circularServices,
            CircularValidator circularValidator,
            ValidationErrorMappingServices validationErrorMappingServices) {

        this.circularServices = circularServices;
        this.circularValidator = circularValidator;
        this.validationErrorMappingServices = validationErrorMappingServices;
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Circular circular, BindingResult result) {
        circularValidator.validate(circular, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(circularServices.saveOrUpdate(circular), HttpStatus.CREATED);
    }

    @GetMapping("/get/circulars")
    public Page<Circular> getAll(@RequestParam Optional<Date> date,
                                 @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<Integer> size) {
        return circularServices.getAll(date.orElse(new Date()), page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(circularServices.get(id), HttpStatus.OK);
    }


}
