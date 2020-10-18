package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.payload.CreateCircularRequest;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CircularValidationErrorMappingService {

    private final ApplicationUserRepository applicationUserRepository;

    private final CircularCategoryRepository circularCategoryRepository;

    public CircularValidationErrorMappingService(
            ApplicationUserRepository applicationUserRepository,
            CircularCategoryRepository circularCategoryRepository) {

        this.applicationUserRepository = applicationUserRepository;
        this.circularCategoryRepository = circularCategoryRepository;
    }

    private void checkCircularCategory(CircularCategory circularCategory, Map<String, String> errorMap) {
        if (circularCategory.getId() == null ||
                !circularCategoryRepository.existsById(circularCategory.getId()) ||
                !circularCategoryRepository.getOne(circularCategory.getId()).equals(circularCategory)) {
            errorMap.put("circular.circularCategory", "invalid circularCategory");
        }
    }

    private void checkCircularTags(List<Tag> tags, Map<String, String> errorMap) {
        if (tags == null) tags = new ArrayList<>();
        for (Tag tag : tags) {

        }
    }

    public ResponseEntity<?> mapCircularValidationErrors(CreateCircularRequest createCircularRequest) {

        Map<String, String> errorMap = new HashMap<>();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(createCircularRequest.getUsername());
        if (applicationUser == null) {
            errorMap.put("username", "invalid user");
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Circular circular = createCircularRequest.getCircular();
        circular.setApplicationUser(applicationUser);

        checkCircularCategory(circular.getCircularCategory(), errorMap);
        checkCircularTags(circular.getTags(), errorMap);


        if (errorMap.size() != 0) {
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        return null;
    }

}
