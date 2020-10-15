package com.dailycircular.dailycircular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentCircular extends Circular {

    private Double rentPerMonth = 0.0;

    @NotBlank(message = "Address can not be empty")
    @Size(max = 100)
    private String address;
}
