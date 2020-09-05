package com.thedailycircular.tdc.repository;

import com.thedailycircular.tdc.model.ApplicationUser;
import com.thedailycircular.tdc.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    EmailVerificationToken findByToken(String token);

    EmailVerificationToken findByApplicationUser(ApplicationUser applicationUser);
}
