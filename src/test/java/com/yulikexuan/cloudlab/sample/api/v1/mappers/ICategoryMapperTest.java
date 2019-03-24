//: com.yulikexuan.cloudlab.sample.api.v1.mappers.ICategoryMapperTest.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ICategoryMapperTest {

    private Category category;
    private ICategoryMapper categoryMapper;

    private Random random;
    private long id;
    private String name;


    @BeforeEach
    void setUp() {
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextInt(10);
        this.name = UUID.randomUUID().toString();
        this.category = new Category();
        this.category.setId(this.id);
        this.category.setName(this.name);
        this.categoryMapper = ICategoryMapper.INSTANCE;
    }

    @DisplayName("Able to convert Category to CategoryDTO - ")
    @Test
    void categoryToCategoryDTO() {

        // When
        CategoryDTO categoryDTO = this.categoryMapper.categoryToCategoryDTO(
                this.category);

        // Then
        assertThat(categoryDTO)
                .as("Should be able to transform a Category to a CategoryDTO.")
                .isNotNull();
        assertThat(categoryDTO.getId()).as("Should have same id.")
                .isEqualTo(this.id);
        assertThat(categoryDTO.getName()).as("Should have same name.")
                .isEqualTo(this.name);
    }

}///:~