package com.dailycircular.dailycircular.payload;

import com.dailycircular.dailycircular.model.Circular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCircularRequest {

    @Email
    @NotBlank
    @Size(max = 100)
    private String username;

    @NotNull
    private @Valid Circular circular;
}
