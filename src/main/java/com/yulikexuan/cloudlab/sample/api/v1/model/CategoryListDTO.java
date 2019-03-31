//: com.yulikexuan.cloudlab.sample.api.v1.model.CategoryListDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapper;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
public class CategoryListDTO {

    private List<CategoryDTO> categories;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryListDTO(@JsonProperty("categories") List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public static CategoryListDTO mapCategoryListToCategoryListDTO(
            List<Category> categories) {

        List<CategoryDTO> dtos = Optional.ofNullable(categories)
                .orElse(List.of())
                .stream()
                .map(ICategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return new CategoryListDTO(dtos);
    }

}///:~