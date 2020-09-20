package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.service.ApplicationUserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    private final ApplicationUserServices applicationUserServices;

    public ApplicationUserController(ApplicationUserServices applicationUserServices) {
        this.applicationUserServices = applicationUserServices;
    }

    @GetMapping("/circulars/{username}")
    public ResponseEntity<?> getCirculars(@PathVariable("username") String username) {
        return new ResponseEntity<>(applicationUserServices.getCirculars(username), HttpStatus.OK);
    }
}
