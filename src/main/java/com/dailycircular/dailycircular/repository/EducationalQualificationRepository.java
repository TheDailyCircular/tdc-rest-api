package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.EducationalQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalQualificationRepository extends JpaRepository<EducationalQualification, Long> {

}
