package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.UserEmailAlreadyRegisteredException;
import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.model.EmailVerificationToken;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import com.dailycircular.dailycircular.repository.EmailVerificationTokenRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationUserServices {

    private final ApplicationUserRepository applicationUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    public ApplicationUserServices(
            ApplicationUserRepository applicationUserRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            EmailVerificationTokenRepository emailVerificationTokenRepository) {

        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
    }

    public ApplicationUser registerNewUser(ApplicationUser newApplicationUser) {
        try {
            newApplicationUser.setPassword(bCryptPasswordEncoder.encode(newApplicationUser.getPassword()));
            return applicationUserRepository.save(newApplicationUser);
        } catch (Exception ex) {
            throw new UserEmailAlreadyRegisteredException(newApplicationUser.getUsername() + " already registered");
        }
    }

    public void createEmailVerificationToken(ApplicationUser applicationUser, String token) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setApplicationUser(applicationUser);
        emailVerificationToken.setToken(token);
        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    public List<Circular> getCirculars(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser.getCirculars();
    }

}
