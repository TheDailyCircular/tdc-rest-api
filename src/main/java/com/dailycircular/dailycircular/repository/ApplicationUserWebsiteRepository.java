package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.ApplicationUserWebsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserWebsiteRepository extends JpaRepository<ApplicationUserWebsite, Long> {
}
