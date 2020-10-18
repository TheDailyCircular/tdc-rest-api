package com.dailycircular.dailycircular.api;

import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.payload.CreateCircularRequest;
import com.dailycircular.dailycircular.service.CircularServices;
import com.dailycircular.dailycircular.validation.CircularValidationErrorMappingService;
import com.dailycircular.dailycircular.validation.ValidationErrorMappingServices;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/circular")
public class CircularController {

    private final CircularServices circularServices;

    private final ValidationErrorMappingServices validationErrorMappingServices;

    private final CircularValidationErrorMappingService circularValidationErrorMappingService;

    public CircularController(
            CircularServices circularServices,
            ValidationErrorMappingServices validationErrorMappingServices,
            CircularValidationErrorMappingService circularValidationErrorMappingService) {
        this.circularServices = circularServices;
        this.validationErrorMappingServices = validationErrorMappingServices;
        this.circularValidationErrorMappingService = circularValidationErrorMappingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrUpdate(
            @RequestBody @Valid CreateCircularRequest createCircularRequest, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorMappingServices.mapValidationErrors(result);
        if (errorMap != null) return errorMap;

        errorMap = circularValidationErrorMappingService.mapCircularValidationErrors(createCircularRequest);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(
                circularServices.saveOrUpdate(createCircularRequest.getCircular()), HttpStatus.CREATED);
    }

    @GetMapping("/circulars")
    public Page<Circular> getAll(@RequestParam Optional<Date> date,
                                 @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<Integer> size) {
        return circularServices.getAll(date.orElse(new Date()), page.orElse(0), size.orElse(5));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return new ResponseEntity<>(circularServices.get(id), HttpStatus.OK);
    }


}
