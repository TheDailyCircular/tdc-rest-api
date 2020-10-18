package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.Company;
import com.dailycircular.dailycircular.model.Degree;
import com.dailycircular.dailycircular.service.CompanyServices;
import com.dailycircular.dailycircular.service.DegreeServices;
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
@RequestMapping(path = "/api/company")
public class CompanyController {
    @Autowired
    private CompanyServices companyServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Company company, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);

        if( errorMap != null ) {
            return errorMap;
        }
        return new ResponseEntity<>(companyServices.saveOrUpdate(company), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Company> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return companyServices.getAll(page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(companyServices.getById(id), HttpStatus.OK);
    }
}
