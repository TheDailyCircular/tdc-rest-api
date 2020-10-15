package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
