package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationUserServices {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServices(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public List<Circular> getCirculars(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser.getCirculars();
    }


}
