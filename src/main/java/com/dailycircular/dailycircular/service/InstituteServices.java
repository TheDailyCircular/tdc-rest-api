package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Degree;
import com.dailycircular.dailycircular.model.Institute;
import com.dailycircular.dailycircular.repository.DegreeRepository;
import com.dailycircular.dailycircular.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InstituteServices {
    @Autowired
    private InstituteRepository instituteRepository;

    public Institute saveOrUpdate(Institute institute) {
        return instituteRepository.save(institute);
    }

    public Page<Institute> getAll(Integer page, Integer size) {
        return instituteRepository.findAll(PageRequest.of(page, size));
    }

    public Institute getById(Long id) {
        if( !instituteRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Institute with id " + id + " does not exist");
        }
        return instituteRepository.getOne(id);
    }
}
