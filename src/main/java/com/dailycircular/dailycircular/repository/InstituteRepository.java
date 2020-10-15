package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {
}
