package com.dailycircular.dailycircular.repository;

import com.dailycircular.dailycircular.model.UserRoleInOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleInOrganizationRepository extends JpaRepository<UserRoleInOrganization, Long> {
}
