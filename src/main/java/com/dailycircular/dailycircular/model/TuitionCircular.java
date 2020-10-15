package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TuitionCircular extends Circular {

    private Integer studentClass;

    @ManyToMany(mappedBy = "tuitionCirculars")
    private List<TuitionCircularSubject> tuitionCircularSubjects = new ArrayList<>();

    @NotBlank(message = "Address can not be empty")
    @Size(max = 100)
    private String address;
}
