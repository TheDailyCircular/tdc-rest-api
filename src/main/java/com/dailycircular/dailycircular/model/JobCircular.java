package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCircular implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private @Valid Company company;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private @Valid JobCircularRole jobCircularRole;

    @Max(100)
    private Integer vacancy = 0;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private @Valid JobCircularEmploymentType jobCircularEmploymentType;

    @Column(columnDefinition = "TEXT")
    private String responsibilities = "";

    @Column(columnDefinition = "TEXT")
    private String educationalQualifications = "";

    @Column(columnDefinition = "TEXT")
    private String skillRequirements = "";

    @Column(columnDefinition = "TEXT")
    private String compensationAndBenefits = "";

    @NotBlank
    private String salary;
}
