package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
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
}
