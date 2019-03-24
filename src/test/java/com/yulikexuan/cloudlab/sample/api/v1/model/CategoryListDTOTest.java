//: com.yulikexuan.cloudlab.sample.api.v1.model.CategoryListDTOTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapper;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class CategoryListDTOTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void mapCategoryListToCategoryListDTO() {

        // Given
        long id1 = 1L;
        long id2 = 2L;
        String name1 = "Fresh";
        String name2 = "Fruits";

        Category category1 = new Category();
        category1.setId(id1);
        category1.setName(name1);

        Category category2 = new Category();
        category1.setId(id2);
        category1.setName(name2);

        CategoryDTO dto1 = ICategoryMapper.INSTANCE.categoryToCategoryDTO(
                category1);
        CategoryDTO dto2 = ICategoryMapper.INSTANCE.categoryToCategoryDTO(
                category2);

        List<Category> categories = List.of(category1, category2);

        // When
        CategoryListDTO listDTO = CategoryListDTO
                .mapCategoryListToCategoryListDTO(categories);

        // Then
        assertThat(listDTO).as("Should CategoryListDTO be not null.")
                .isNotNull();
        assertThat(listDTO.getCategories())
                .as("Should having %d same CategoryDTOs.",
                        categories.size())
                .containsExactly(new CategoryDTO[] {dto1, dto2});
    }

}///:~