//: com.yulikexuan.cloudlab.sample.api.v1.model.CategoryListDTO.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapper;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categories;

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