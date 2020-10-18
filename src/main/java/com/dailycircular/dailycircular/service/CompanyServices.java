package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Company;
import com.dailycircular.dailycircular.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyServices {
    @Autowired
    private CompanyRepository companyRepository;

    public Company saveOrUpdate(Company company) {
        return companyRepository.save(company);
    }

    public Page<Company> getAll(Integer page, Integer size) {
        return companyRepository.findAll(PageRequest.of(page, size));
    }

    public Company getById(Long id) {
        if( !companyRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Company with id " + id + " does not exist");
        }
        return companyRepository.getOne(id);
    }
}
