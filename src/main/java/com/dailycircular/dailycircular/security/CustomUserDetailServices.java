package com.dailycircular.dailycircular.security;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailServices implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    public CustomUserDetailServices(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = getApplicationUserByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    @Transactional
    ApplicationUser getApplicationUserByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }
}
