package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.OrganizationRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRatingRepository extends JpaRepository<OrganizationRating, Long> {
}
