package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.ApplicationUserRoleInOrganization;
import com.dailycircular.dailycircular.repository.ApplicationUserRoleInOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApplicationUserRoleInOrganizationServices {
    @Autowired
    private ApplicationUserRoleInOrganizationRepository applicationUserRoleInOrganizationRepository;

    public ApplicationUserRoleInOrganization saveOrUpdate(ApplicationUserRoleInOrganization applicationUserRoleInOrganization) {
        return applicationUserRoleInOrganizationRepository.save(applicationUserRoleInOrganization);
    }

    public Page<ApplicationUserRoleInOrganization> getAll(Integer page, Integer size) {
        return applicationUserRoleInOrganizationRepository.findAll(PageRequest.of(page, size));
    }

    public boolean existsById(Long id) {
        return applicationUserRoleInOrganizationRepository.existsById(id);
    }

    public ApplicationUserRoleInOrganization getById(Long id) {
        if( !applicationUserRoleInOrganizationRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Application user role in organization with ID: " + id + " does not exist");
        }
        return applicationUserRoleInOrganizationRepository.getOne(id);
    }
}
