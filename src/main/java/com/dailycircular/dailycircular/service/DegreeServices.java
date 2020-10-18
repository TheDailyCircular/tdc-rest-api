package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.Degree;
import com.dailycircular.dailycircular.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DegreeServices {
    @Autowired
    private DegreeRepository degreeRepository;

    public Degree saveOrUpdate(Degree degree) {
        return degreeRepository.save(degree);
    }

    public Page<Degree> getAll(Integer page, Integer size) {
        return degreeRepository.findAll(PageRequest.of(page, size));
    }

    public boolean existsById(Long id) {
        return degreeRepository.existsById(id);
    }

    public Degree getById(Long id) {
        if( !degreeRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Degree with id " + id + " does not exist");
        }
        return degreeRepository.getOne(id);
    }
}
