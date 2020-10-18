package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.CompanyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeRepository extends JpaRepository<CompanyType, Long> {

    Page<CompanyType> findAll(Pageable pageable);
}
