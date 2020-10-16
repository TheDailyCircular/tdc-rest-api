package com.dailycircular.dailycircular.payload;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTagChoiceUpdateRequest {

    @NotNull
    private Long applicationUserId;

    @NotNull
    @Size(min = 2, message = "choose at least 2 categories")
    private Set<@Valid CircularCategory> circularCategories;

    @NotNull
    @Size(min = 2, message = "choose at least 2 tags")
    private Set<@Valid Tag> tags;
}
