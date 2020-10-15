package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.UserCircularActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCircularActionsRepository extends JpaRepository<UserCircularActions, Long> {
}
