package com.thedailycircular.tdc.repository;

import com.thedailycircular.tdc.model.EducationalQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalQualificationRepository extends JpaRepository<EducationalQualification, Long> {

}
