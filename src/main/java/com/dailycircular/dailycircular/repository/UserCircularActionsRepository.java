package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.ApplicationUserAndCircularAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCircularActionsRepository extends JpaRepository<ApplicationUserAndCircularAction, Long> {
}
