package com.thedailycircular.tdc.repository;

import com.thedailycircular.tdc.model.EmailVerificationToken;
import com.thedailycircular.tdc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    EmailVerificationToken findByToken(String token);

    EmailVerificationToken findByUser(User user);
}
