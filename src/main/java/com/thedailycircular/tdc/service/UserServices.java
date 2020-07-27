package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.UserEmailAlreadyRegisteredException;
import com.thedailycircular.tdc.model.User;
import com.thedailycircular.tdc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDB = userRepository.findByUsername(username);
        if (userFromDB == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                userFromDB.getUsername(),
                userFromDB.getPassword(),
                null // authority will be updated
        );
    }

    public User registerNewUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        } catch (Exception ex) {
            throw new UserEmailAlreadyRegisteredException(newUser.getUsername() + " already registered");
        }
    }
}
