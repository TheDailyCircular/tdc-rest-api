package com.thedailycircular.tdc.repository;

import com.thedailycircular.tdc.model.Circular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularRepository extends JpaRepository<Circular, Long> {
}
