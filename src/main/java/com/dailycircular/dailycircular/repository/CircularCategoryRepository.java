package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.CircularCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularCategoryRepository extends JpaRepository<CircularCategory, Long> {
    Page<CircularCategory> findAll(Pageable pageable);
}
