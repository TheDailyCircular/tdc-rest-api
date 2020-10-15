package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserAndCircularAction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ApplicationUser applicationUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Circular circular;

    private boolean isLiked = false;

    private boolean isSaved = false;

    private boolean isApplied = false;
}
