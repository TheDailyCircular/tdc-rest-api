package com.thedailycircular.tdc.payload;

import com.thedailycircular.tdc.model.ApplicationUser;
import com.thedailycircular.tdc.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest extends AuthenticationRequest {

    private String confirmPassword;

    @NotBlank(message = "firstName can not be empty")
    private String firstName;

    @NotBlank(message = "lastName can not be empty")
    private String lastName;

    public ApplicationUser createUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setPassword(password);

        Resume resume = new Resume();
        resume.setFirstName(firstName);
        resume.setLastName(lastName);
        applicationUser.setResume(resume);

        return applicationUser;
    }
}
