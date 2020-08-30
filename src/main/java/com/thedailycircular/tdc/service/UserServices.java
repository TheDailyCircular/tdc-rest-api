package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.UserEmailAlreadyRegisteredException;
import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.model.EmailVerificationToken;
import com.thedailycircular.tdc.model.User;
import com.thedailycircular.tdc.repository.EmailVerificationTokenRepository;
import com.thedailycircular.tdc.repository.UserRepository;
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
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public User registerNewUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        } catch (Exception ex) {
            throw new UserEmailAlreadyRegisteredException(newUser.getUsername() + " already registered");
        }
    }

    public List<Circular> getCirculars(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user.getCirculars();
    }

    public void createEmailVerificationToken(User user, String token) {
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setUser(user);
        emailVerificationToken.setToken(token);
        emailVerificationTokenRepository.save(emailVerificationToken);
    }
}
