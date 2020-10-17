package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.Degree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    Page<Degree> findAll(Pageable pageable);
}
