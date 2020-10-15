package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.payload.CategoryTagChoiceUpdateRequest;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
import com.dailycircular.dailycircular.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class CategoryTagChoiceUpdateRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryTagChoiceUpdateRequest.class.equals(aClass);
    }

    @Autowired
    private CircularCategoryRepository circularCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void validate(Object o, Errors errors) {
        CategoryTagChoiceUpdateRequest categoryTagChoiceUpdateRequest = (CategoryTagChoiceUpdateRequest) o;

        List<CircularCategory> categories = categoryTagChoiceUpdateRequest.getCircularCategories();
        List<Tag> tags = categoryTagChoiceUpdateRequest.getTags();

        if (categories.size() < 2) {
            errors.rejectValue("categories", "categories", "please choose at least 2 categories.");
        }
        if (tags.size() < 2) {
            errors.rejectValue("tags", "tags", "please choose at least 2 tags.");
        }

        for (CircularCategory circularCategory :
                categories) {
            if (circularCategory.getId() == null ||
                    !circularCategoryRepository.existsById(circularCategory.getId()) ||
                    !circularCategoryRepository.getOne(circularCategory.getId()).equals(circularCategory)) {
                errors.rejectValue(
                        "id", "id", "invalid category id"
                );
            }
        }

        for (Tag tag :
                tags) {
            if (tag.getId() == null ||
                    !tagRepository.existsById(tag.getId()) ||
                    !tagRepository.getOne(tag.getId()).equals(tag)) {
                errors.rejectValue(
                        "id", "id", "invalid tag id"
                );
            }
        }
    }
}
