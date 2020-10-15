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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserWebsite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "website name can not be empty")
    @Size(max = 100)
    private String websiteName;

    @NotBlank(message = "website url can not be empty")
    @Size(max = 250)
    private String websiteURL;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Resume resume;
}
