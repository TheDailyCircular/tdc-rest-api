package com.dailycircular.dailycircular.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Circular implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Circular Title can not be empty")
    @Column(length = 500)
    private String title;

    @NotBlank(message = "Circular Text can not be empty")
    @Column(columnDefinition = "TEXT")
    private String text;

    private Date expirationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ApplicationUser applicationUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

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
