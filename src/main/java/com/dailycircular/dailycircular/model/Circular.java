package com.dailycircular.dailycircular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Circular implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Circular Title can not be empty")
    @Column(length = 600)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CircularCategory circularCategory;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @NotBlank(message = "Circular Text can not be empty")
    @Column(columnDefinition = "TEXT")
    private String description;

    private Date expirationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ApplicationUser applicationUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    private CircularRating circularRating;

    @JsonIgnore
    @OneToMany(mappedBy = "circular", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApplicationUserAndCircularAction> applicationUserAndCircularActions = new ArrayList<>();

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

    public Boolean isExpired() {
        return (new Date().after(this.expirationDate));
    }

}
