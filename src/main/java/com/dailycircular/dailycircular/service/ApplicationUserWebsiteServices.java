package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.ApplicationUserWebsite;
import com.dailycircular.dailycircular.repository.ApplicationUserWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApplicationUserWebsiteServices {
    @Autowired
    private ApplicationUserWebsiteRepository applicationUserWebsiteRepository;

    public ApplicationUserWebsite saveOrUpdate(ApplicationUserWebsite applicationUserWebsite) {
        return applicationUserWebsiteRepository.save(applicationUserWebsite);
    }

    public ApplicationUserWebsite getById(Long id) {
        if( !applicationUserWebsiteRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Application user website with ID: " + id + " does not exist");
        }
        return applicationUserWebsiteRepository.getOne(id);
    }
}
