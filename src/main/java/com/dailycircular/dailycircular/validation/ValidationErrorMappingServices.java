package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.payload.CategoryTagChoiceUpdateRequest;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
import com.dailycircular.dailycircular.repository.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorMappingServices {

    private final ApplicationUserRepository applicationUserRepository;

    private final CircularCategoryRepository circularCategoryRepository;

    private final TagRepository tagRepository;

    public ValidationErrorMappingServices(
            ApplicationUserRepository applicationUserRepository,
            CircularCategoryRepository circularCategoryRepository,
            TagRepository tagRepository) {

        this.applicationUserRepository = applicationUserRepository;
        this.circularCategoryRepository = circularCategoryRepository;
        this.tagRepository = tagRepository;
    }

    public ResponseEntity<?> mapValidationErrors(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<?> mapCategoryTagChoiceUpdateRequestValidationErrors(
            CategoryTagChoiceUpdateRequest categoryTagChoiceUpdateRequest) {

        Map<String, String> errorMap = new HashMap<>();
        if (!applicationUserRepository.existsById(categoryTagChoiceUpdateRequest.getApplicationUserId())) {
            errorMap.put("applicationUserId", "invalid user");
        }
        for (CircularCategory circularCategory :
                categoryTagChoiceUpdateRequest.getCircularCategories()) {
            if (circularCategory.getId() == null ||
                    !circularCategoryRepository.existsById(circularCategory.getId()) ||
                    !circularCategoryRepository.getOne(circularCategory.getId()).equals(circularCategory)) {
                errorMap.put("circularCategories", "invalid categories");
            }
        }
        for (Tag tag :
                categoryTagChoiceUpdateRequest.getTags()) {
            if (tag.getId() == null ||
                    !tagRepository.existsById(tag.getId()) ||
                    !tagRepository.getOne(tag.getId()).equals(tag)) {
                errorMap.put("tags", "invalid tags");
            }
        }
        if (errorMap.size() == 0) return null;
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
