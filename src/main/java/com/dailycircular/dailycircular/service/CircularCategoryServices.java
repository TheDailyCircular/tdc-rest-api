package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CircularCategoryServices {

    private final CircularCategoryRepository circularCategoryRepository;

    public CircularCategoryServices(CircularCategoryRepository circularCategoryRepository) {
        this.circularCategoryRepository = circularCategoryRepository;
    }

    public CircularCategory saveOrUpdate(CircularCategory circularCategory) {
        return circularCategoryRepository.save(circularCategory);
    }

    public boolean existsById(Long id) {
        return circularCategoryRepository.existsById(id);
    }

    public CircularCategory getById(Long id) {
        if (!circularCategoryRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Circular category with id " + id + " does not exists");
        }
        return circularCategoryRepository.getOne(id);
    }

    public Page<CircularCategory> getAll(Integer page, Integer size) {
        return circularCategoryRepository.findAll(PageRequest.of(page, size));
    }
}
