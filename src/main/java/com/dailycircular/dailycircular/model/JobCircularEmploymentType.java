package com.dailycircular.dailycircular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCircularEmploymentType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String employmentType;

    @JsonIgnore
    @OneToMany(mappedBy = "jobCircularEmploymentType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<JobCircular> jobCirculars = new ArrayList<>();
}
