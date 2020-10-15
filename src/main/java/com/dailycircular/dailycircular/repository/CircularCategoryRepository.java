package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.CircularCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularCategoryRepository extends JpaRepository<CircularCategory, Long> {
}
