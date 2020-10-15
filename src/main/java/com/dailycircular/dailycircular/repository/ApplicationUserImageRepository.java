package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.ApplicationUserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserImageRepository extends JpaRepository<ApplicationUserImage, Long> {
}
