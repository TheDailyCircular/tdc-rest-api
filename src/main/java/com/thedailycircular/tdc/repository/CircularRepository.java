package com.thedailycircular.tdc.repository;

import com.thedailycircular.tdc.model.Circular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CircularRepository extends JpaRepository<Circular, Long> {

    @Query("SELECT c FROM Circular c where c.expirationDate >= ?1")
    Page<Circular> findAll(Date date, Pageable pageable);
}
