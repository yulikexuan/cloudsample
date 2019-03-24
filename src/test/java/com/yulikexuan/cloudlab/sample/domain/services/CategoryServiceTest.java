//: com.yulikexuan.cloudlab.sample.domain.services.CategoryServiceTest.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Able to get all categories - ")
    @Test
    void getAllCategories() {

        // Given
        List<Category> expectedAllCategories = List.of(new Category(),
                new Category());
        Category[] categoryArr = {};

        given(this.categoryRepository.findAll())
                .willReturn(expectedAllCategories);

        // When
        List<Category> actualAllCategories =
                this.categoryService.getAllCategories();

        // Then
        assertThat(actualAllCategories)
                .as("Should be same as the expected categories.")
                .containsExactly(expectedAllCategories.toArray(categoryArr));
    }

    @DisplayName("Able to get a Category by it's name - ")
    @Test
    void getCategoryByName() {

        // Given
        String name = UUID.randomUUID().toString();
        long id = 7L;

        Category expectedCategory = new Category();
        expectedCategory.setId(id);
        expectedCategory.setName(name);

        given(this.categoryRepository.findByName(name))
                .willReturn(expectedCategory);

        // When
        Optional<Category> actualCategoryOpt =
                this.categoryService.getCategoryByName(name);

        // Then
        assertThat(actualCategoryOpt.get())
                .as("Should be same as the expected Category.")
                .isSameAs(expectedCategory);
    }

}///:~