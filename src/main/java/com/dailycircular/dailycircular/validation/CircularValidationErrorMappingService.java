package com.dailycircular.dailycircular.validation;

import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.payload.CreateCircularRequest;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import com.dailycircular.dailycircular.repository.CircularCategoryRepository;
import com.dailycircular.dailycircular.service.ApplicationUserServices;
import com.dailycircular.dailycircular.service.CircularCategoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dailycircular.dailycircular.constants.ValidationConstants.CIRCULAR_CATEGORY_NAMES;


@Component
public class CircularValidationErrorMappingService {

    private final ApplicationUserServices applicationUserServices;

    private final CircularCategoryServices circularCategoryServices;

    private final JobCircularValidationErrorMappingService jobCircularValidationErrorMappingService;

    public CircularValidationErrorMappingService(
            ApplicationUserServices applicationUserServices,
            CircularCategoryServices circularCategoryServices,
            JobCircularValidationErrorMappingService jobCircularValidationErrorMappingService) {

        this.applicationUserServices = applicationUserServices;
        this.circularCategoryServices = circularCategoryServices;
        this.jobCircularValidationErrorMappingService = jobCircularValidationErrorMappingService;
    }

    private void checkCircularCategory(CircularCategory circularCategory, Map<String, String> errorMap) {
        if (circularCategory.getId() == null ||
                !circularCategoryServices.existsById(circularCategory.getId()) ||
                !circularCategoryServices.getById(circularCategory.getId()).equals(circularCategory)) {
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
        ApplicationUser applicationUser = applicationUserServices.getUserByUserName(createCircularRequest.getUsername());
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

        if (circular.getCircularCategory().getCategoryName().equals(CIRCULAR_CATEGORY_NAMES.get(0))) { /** job circular */
            circular.setAdmissionCircular(null);
            circular.setRentCircular(null);
            circular.setTuitionCircular(null);

            jobCircularValidationErrorMappingService.validateJobCircular(circular.getJobCircular(), errorMap);

            if (errorMap.size() != 0) {
                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            }
        } else if (circular.getCircularCategory().getCategoryName().equals(CIRCULAR_CATEGORY_NAMES.get(1))) { /** admission circular */
            circular.setJobCircular(null);
            circular.setRentCircular(null);
            circular.setTuitionCircular(null);

            //

            if (errorMap.size() != 0) {
                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            }
        }
        else if (circular.getCircularCategory().getCategoryName().equals(CIRCULAR_CATEGORY_NAMES.get(2))) { /** tuition circular */
            circular.setJobCircular(null);
            circular.setRentCircular(null);
            circular.setAdmissionCircular(null);

            //

            if (errorMap.size() != 0) {
                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            }
        }
        else if (circular.getCircularCategory().getCategoryName().equals(CIRCULAR_CATEGORY_NAMES.get(3))) { /** rent circular */
            circular.setJobCircular(null);
            circular.setAdmissionCircular(null);
            circular.setTuitionCircular(null);

            //

            if (errorMap.size() != 0) {
                return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
            }
        }

        return null;
    }

}
