package com.dailycircular.dailycircular.repository;


import com.dailycircular.dailycircular.model.ApplicationUserRoleInOrganization;
import com.dailycircular.dailycircular.model.Circular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ApplicationUserRoleInOrganizationRepository extends JpaRepository<ApplicationUserRoleInOrganization, Long> {

    boolean existsByRoleName(String roleName);

    Page<ApplicationUserRoleInOrganization> findAll(Pageable pageable);
}
