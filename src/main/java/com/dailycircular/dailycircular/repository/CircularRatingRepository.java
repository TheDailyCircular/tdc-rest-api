package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.CircularRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircularRatingRepository extends JpaRepository<CircularRating, Long> {
}
