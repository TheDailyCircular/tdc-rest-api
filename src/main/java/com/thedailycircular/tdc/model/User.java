package com.thedailycircular.tdc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"circulars", "organizations", "notifications", "messages"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "username must be a valid email")
    @NotBlank(message = "username can not be empty")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "firstName can not be empty")
    private String firstName;

    @NotBlank(message = "lastName can not be empty")
    private String lastName;

    @NotBlank(message = "password can not be empty")
    private String password;

    @Transient
    private String confirmPassword;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Circular> circulars = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Organization> organizations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Resume resume;

    private Date createdAt;

    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
