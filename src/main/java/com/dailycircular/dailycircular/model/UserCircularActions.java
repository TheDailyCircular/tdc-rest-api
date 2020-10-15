package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCircularActions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isLiked = false;

    private boolean isSaved = false;

    private boolean isApplied = false;

    /**
     * version 2.0 addition
     */
    private ApplicationUser applicationUser;
    private Circular circular;
}
