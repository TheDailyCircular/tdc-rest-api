package com.thedailycircular.tdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static com.thedailycircular.tdc.security.SecurityConstants.EMAIL_VERIFICATION_TOKEN_DURATION;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    private Date expirationDate;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        Date now = new Date(System.currentTimeMillis());
        this.expirationDate = new Date(now.getTime() + EMAIL_VERIFICATION_TOKEN_DURATION);
    }

}
