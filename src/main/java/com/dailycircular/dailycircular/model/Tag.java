package com.dailycircular.dailycircular.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"circulars", "applicationUsers"})
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String tagName;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Circular> circulars = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<ApplicationUser> applicationUsers = new ArrayList<>();
}
