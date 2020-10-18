package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.model.CompanyType;
import com.dailycircular.dailycircular.repository.CompanyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyTypeServices {

    @Autowired
    private CompanyTypeRepository companyTypeRepository;

    public Page<CompanyType> getAll(Integer page, Integer size) {
        return companyTypeRepository.findAll(PageRequest.of(page, size));
    }

    public boolean existsById(Long id) {
        return companyTypeRepository.existsById(id);
    }
}
