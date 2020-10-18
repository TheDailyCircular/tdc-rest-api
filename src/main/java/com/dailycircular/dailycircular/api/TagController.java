package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.service.TagServices;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/tag")
public class TagController {
    @Autowired
    private TagServices tagServices;

    @Autowired
    private ValidationErrorMappingServices validationErrorMappingServices;

    @PostMapping("/create")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Tag tag, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);

        if( errorMap != null ) {
            return errorMap;
        }
        return new ResponseEntity<>(tagServices.saveOrUpdate(tag), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Tag> getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return tagServices.getAll(page.orElse(0), size.orElse(5));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(tagServices.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tagServices.delete(id);
        return new ResponseEntity<>("Tag with id " + id + " deleted successfully", HttpStatus.OK);
    }

}
