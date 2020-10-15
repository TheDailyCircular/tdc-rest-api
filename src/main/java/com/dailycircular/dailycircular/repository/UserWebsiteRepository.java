package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.UserWebsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWebsiteRepository extends JpaRepository<UserWebsite, Long> {
}
