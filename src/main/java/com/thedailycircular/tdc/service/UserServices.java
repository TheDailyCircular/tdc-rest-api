package com.thedailycircular.tdc.service;

import com.thedailycircular.tdc.exception.UserEmailAlreadyRegisteredException;
import com.thedailycircular.tdc.model.Circular;
import com.thedailycircular.tdc.model.User;
import com.thedailycircular.tdc.repository.UserRepository;
import com.thedailycircular.tdc.security.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
}
