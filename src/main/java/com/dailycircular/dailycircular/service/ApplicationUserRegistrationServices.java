package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.ApplicationUserExceptions.ApplicationUserNameNotFoundException;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.EmailVerificationFailedException;
import com.dailycircular.dailycircular.exception.ApplicationUserRegistrationExceptions.UserEmailAlreadyRegisteredException;
import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.EmailVerificationToken;
import com.dailycircular.dailycircular.payload.EmailVerificationRequest;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import com.dailycircular.dailycircular.repository.EmailVerificationTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.dailycircular.dailycircular.constants.SecurityConstants.EMAIL_CONFIRMATION_TOKEN_FORMAT;
import static com.dailycircular.dailycircular.constants.SecurityConstants.EMAIL_CONFIRMATION_TOKEN_GENERATOR_LOWER_LIMIT;

@Service
@Transactional
public class ApplicationUserRegistrationServices {

    private final ApplicationUserRepository applicationUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    public ApplicationUserRegistrationServices(
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
            throw ex; //new UserEmailAlreadyRegisteredException(newApplicationUser.getUsername() + " already registered");
        }
    }

    public EmailVerificationToken createEmailVerificationToken(ApplicationUser applicationUser) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setApplicationUser(applicationUser);
        emailVerificationToken.setToken(createRandomSixDigitCode());
        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    private String createRandomSixDigitCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(EMAIL_CONFIRMATION_TOKEN_GENERATOR_LOWER_LIMIT);
        return String.format(EMAIL_CONFIRMATION_TOKEN_FORMAT, number);
    }

    public Object verifyEmail(EmailVerificationRequest emailVerificationRequest) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(emailVerificationRequest.getUsername());
        if (applicationUser == null) {
            throw new ApplicationUserNameNotFoundException(emailVerificationRequest.getUsername() + " does not exist");
        }
        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByApplicationUser(applicationUser);
        if (emailVerificationToken == null) {
            createEmailVerificationToken(applicationUser);
            throw new EmailVerificationFailedException("invalid token, check your email and try again");
        }
        if (!emailVerificationRequest.getToken().equals(emailVerificationToken.getToken())) {
            throw new EmailVerificationFailedException("invalid token, check your email and try again");
        }
        applicationUser.setIsEnabled(true);
        applicationUserRepository.save(applicationUser);
        return "Email verification is successful";
    }
}
