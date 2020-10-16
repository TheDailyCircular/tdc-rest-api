package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.ApplicationUserRoleInOrganization;
import com.dailycircular.dailycircular.service.ApplicationUserRoleInOrganizationServices;
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
@RequestMapping(path = "/api/user_role_in_organization")
public class ApplicationUserRoleInOrganizationController {
    @Autowired
    private ApplicationUserRoleInOrganizationServices applicationUserRoleInOrganizationServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody ApplicationUserRoleInOrganization applicationUserRoleInOrganization, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(applicationUserRoleInOrganizationServices.saveOrUpdate(applicationUserRoleInOrganization), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<ApplicationUserRoleInOrganization> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return applicationUserRoleInOrganizationServices.getAll(page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(applicationUserRoleInOrganizationServices.getById(id), HttpStatus.OK);
    }
}
