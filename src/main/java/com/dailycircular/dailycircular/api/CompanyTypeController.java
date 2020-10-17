package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.CompanyType;
import com.dailycircular.dailycircular.service.CompanyTypeServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/company_type")
public class CompanyTypeController {

    @Autowired
    private CompanyTypeServices companyTypeServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @GetMapping("/get/all")
    public Page<CompanyType> getAll(@RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> size) {
        return companyTypeServices.getAll(page.orElse(0), size.orElse(5));
    }
}
