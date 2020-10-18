package com.dailycircular.dailycircular.model;

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
public class RentCircular implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rentPerMonth = 0.0;

    @NotBlank(message = "Address can not be empty")
    @Size(max = 100)
    private String address;

    @OneToMany(mappedBy = "rentCircular", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RentCircularImage> rentCircularImages = new ArrayList<>();
}
