package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.Organization;
import com.dailycircular.dailycircular.service.OrganizationServices;
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
@RequestMapping(path = "/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationServices organizationServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Organization organization, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);

        if( errorMap != null ) {
            return errorMap;
        }
        return new ResponseEntity<>(organizationServices.saveOrUpdate(organization), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Organization> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return organizationServices.getAll(page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(organizationServices.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        organizationServices.delete(id);
        return new ResponseEntity<>("Organization with id " + id + " deleted successfully", HttpStatus.OK);
    }
}
