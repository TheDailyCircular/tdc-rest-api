package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.Institute;
import com.dailycircular.dailycircular.service.InstituteServices;
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
@RequestMapping(path = "/api/institute")
public class InstituteController {
    @Autowired
    private InstituteServices instituteServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Institute institute, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);

        if( errorMap != null ) {
            return errorMap;
        }
        return new ResponseEntity<>(instituteServices.saveOrUpdate(institute), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Institute> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return instituteServices.getAll(page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(instituteServices.getById(id), HttpStatus.OK);
    }
}
