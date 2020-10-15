package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.RentCircularImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentCircularImageRepository extends JpaRepository<RentCircularImage, Long> {
}
