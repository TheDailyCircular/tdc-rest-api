package com.dailycircular.dailycircular.service;

import com.dailycircular.dailycircular.exception.EntityIdNotFoundException;
import com.dailycircular.dailycircular.model.ApplicationUser;
import com.dailycircular.dailycircular.model.Circular;
import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import com.dailycircular.dailycircular.payload.CategoryTagChoiceUpdateRequest;
import com.dailycircular.dailycircular.repository.ApplicationUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class ApplicationUserServices {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServices(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public boolean existsById(Long id) {
        return applicationUserRepository.existsById(id);
    }

    public List<Circular> getCirculars(String username) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return applicationUser.getCirculars();
    }

    /**
     * applicationUserId  must be valid
     * circularCategories must be valid
     * tags               must be valid
     */
    public CategoryTagChoiceUpdateRequest updateCircularCategoryAndTagChoice(
            CategoryTagChoiceUpdateRequest categoryTagChoiceUpdateRequest) {

        ApplicationUser applicationUser =
                applicationUserRepository.getOne(categoryTagChoiceUpdateRequest.getApplicationUserId());

        List<CircularCategory> circularCategories = new ArrayList<>(categoryTagChoiceUpdateRequest.getCircularCategories());
        List<Tag> tags = new ArrayList<>(categoryTagChoiceUpdateRequest.getTags());

        applicationUser.setCircularCategories(circularCategories);
        applicationUser.setTags(tags);

        ApplicationUser applicationUserUpdated = applicationUserRepository.save(applicationUser);

        return new CategoryTagChoiceUpdateRequest(
                applicationUserUpdated.getId(),
                new HashSet<>(applicationUserUpdated.getCircularCategories()),
                new HashSet<>(applicationUserUpdated.getTags()));
    }

    public ApplicationUser getUserByUserId(Long id) {
        if (!applicationUserRepository.existsById(id)) {
            throw new EntityIdNotFoundException("user with " + id + " does not exists");
        }
        return applicationUserRepository.getOne(id);
    }
}
