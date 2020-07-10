package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.EntityIdNotFoundException;
import com.thedailycircular.tdc.model.Organization;
import com.thedailycircular.tdc.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganizationServices {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization saveOrUpdate(Organization organization) {
        return organizationRepository.save(organization);
    }

    public List<Organization> getAll() {
        return organizationRepository.findAll();
    }

    public Organization get(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new EntityIdNotFoundException("Organization with ID: " + id + " doesn't exist");
        }
        return organizationRepository.getOne(id);
    }

    public void delete(Long id) {
        if( !organizationRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Organization with ID: " + id + " doesn't exist");
        }
        organizationRepository.deleteById(id);
    }
}
