package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCircular extends Circular {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private JobCircularRole jobCircularRole;

    @Max(100)
    private Integer vacancy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private JobCircularEmploymentType jobCircularEmploymentType;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

    @Column(columnDefinition = "TEXT")
    private String educationalQualifications;

    @Column(columnDefinition = "TEXT")
    private String skillRequirements;

    @Column(columnDefinition = "TEXT")
    private String compensationAndBenefits;

    private String salary;
}
