package com.dailycircular.dailycircular.payload;

import com.dailycircular.dailycircular.model.CircularCategory;
import com.dailycircular.dailycircular.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTagChoiceUpdateRequest {

    private List<@Valid CircularCategory> circularCategories = new ArrayList<>();

    private List<@Valid Tag> tags = new ArrayList<>();
}
