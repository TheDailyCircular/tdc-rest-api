package com.dailycircular.dailycircular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can not be empty")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "organization", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Circular> circulars = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    private OrganizationRating organizationRating;

    @JsonIgnore
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApplicationUserAndOrganizationRelation> applicationUserAndOrganizationRelations = new ArrayList<>();

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
