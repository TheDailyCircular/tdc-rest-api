package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.ApplicationUserImage;
import com.dailycircular.dailycircular.repository.ApplicationUserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ApplicationUserImageServices {
    @Autowired
    private ApplicationUserImageRepository applicationUserImageRepository;

    public ApplicationUserImage saveOrUpdate(ApplicationUserImage applicationUserImage) {
        return applicationUserImageRepository.save(applicationUserImage);
    }

    public ApplicationUserImage getById(Long id) {
        if( !applicationUserImageRepository.existsById(id) ) {
            throw new EntityIdNotFoundException("Application user image with ID: " + id + " does not exist");
        }
        return applicationUserImageRepository.getOne(id);
    }
}
