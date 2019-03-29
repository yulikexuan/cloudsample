//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryControllerTest.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController controller;

    private Category category;
    private long id;
    private String name;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        this.id = 7L;
        this.name = "Yul";

        this.category = Category.builder().id(this.id).name(this.name).build();

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @Test
    void getallCatetories() {
    }

    @DisplayName("Able to find a Category by it's name - ")
    @Test
    void getCategoryByName() throws Exception {

        // Given
        Optional<Category> categoryOpt = Optional.of(this.category);
        given(this.categoryService.getCategoryByName(this.name))
                .willReturn(categoryOpt);

        // When
        this.mockMvc.perform(get("/api/v1/categories/" + this.name))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is((int)this.id)));
    }

}///:~