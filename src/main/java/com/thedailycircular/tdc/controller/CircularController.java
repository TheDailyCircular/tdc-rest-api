package com.thedailycircular.tdc.controller;

import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.service.CircularServices;
import com.thedailycircular.tdc.validation.CircularValidator;
import com.thedailycircular.tdc.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "circular")
public class CircularController {

    @Autowired
    private CircularServices circularServices;

    @Autowired
    private CircularValidator circularValidator;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Circular circular, BindingResult result) {
        circularValidator.validate(circular, result);
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(circularServices.saveOrUpdate(circular), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Circular> getAll() {
        return circularServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(circularServices.get(id), HttpStatus.OK);
    }


}
