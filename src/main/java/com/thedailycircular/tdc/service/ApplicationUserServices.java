package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.UserEmailAlreadyRegisteredException;
import com.thedailycircular.tdc.model.ApplicationUser;
import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.model.EmailVerificationToken;
import com.thedailycircular.tdc.repository.EmailVerificationTokenRepository;
import com.thedailycircular.tdc.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationUserServices implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser;
    }

    public ApplicationUser registerNewUser(ApplicationUser newApplicationUser) {
        try {
            newApplicationUser.setPassword(bCryptPasswordEncoder.encode(newApplicationUser.getPassword()));
            return applicationUserRepository.save(newApplicationUser);
        } catch (Exception ex) {
            throw new UserEmailAlreadyRegisteredException(newApplicationUser.getUsername() + " already registered");
        }
    }

    public List<Circular> getCirculars(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser.getCirculars();
    }

    public void createEmailVerificationToken(ApplicationUser applicationUser, String token) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setApplicationUser(applicationUser);
        emailVerificationToken.setToken(token);
        emailVerificationTokenRepository.save(emailVerificationToken);
    }
}
