//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryControllerTest.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.services.ICategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        this.name = "Steves Jobs";

        this.category = Category.builder().id(this.id).name(this.name).build();

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @AfterEach
    void tearDown() {
        reset(this.categoryService);
    }

    @DisplayName("Able to find a Category by it's name - ")
    @Test
    void getCategoryByName() throws Exception {

        // Given
        Optional<Category> categoryOpt = Optional.of(this.category);
        given(this.categoryService.getCategoryByName(this.name))
                .willReturn(categoryOpt);

        // When
        this.mockMvc.perform(get("/api/v1/categories/" + this.name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is((int)this.id)));
    }


    @DisplayName("Multi-Category Test - ")
    @Nested
    class CategoryListOperationTest {

        private Category category2;
        private long id2;
        private String name2;

        private List<Category> allCategories;

        @BeforeEach
        void setUp() {
            this.id2 = 16L;
            this.name2 = "Bill Gates";
            this.category2 = Category.builder().id(this.id2).name(this.name2).build();
            this.allCategories = List.of(category, category2);
        }

        @DisplayName("Able to get all categories back - ")
        @Test
        void testGetAllCategories() throws Exception {

            // Given
            given(categoryService.getAllCategories()).willReturn(this.allCategories);

            // When
            mockMvc.perform(get("/api/v1/categories")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.categories", hasSize(2)))
                    .andExpect(jsonPath("$.categories[0].id", is((int)id)))
                    .andExpect(jsonPath("$.categories[0].name", is(name)))
                    .andExpect(jsonPath("$.categories[1].id", is((int)id2)))
                    .andExpect(jsonPath("$.categories[1].name", is(name2)));

        }
    }

}///:~