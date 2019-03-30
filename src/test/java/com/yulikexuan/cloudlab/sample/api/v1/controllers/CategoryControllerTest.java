//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryControllerTest.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.DateMapper;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.services.ICategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
    private Supplier<MappingJackson2HttpMessageConverter>
            messageConverterSupplier = () -> {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        false);
                objectMapper.configure(
                        SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
                        true);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                objectMapper.registerModule(new JavaTimeModule());
                return new MappingJackson2HttpMessageConverter(objectMapper);
            };

    @BeforeEach
    void setUp() {

        this.id = 7L;
        this.name = "Steves Jobs";

        this.category = Category.builder()
                .id(this.id)
                .name(this.name)
                .createdDate(Timestamp.valueOf(OffsetDateTime.now()
                        .toLocalDateTime()))
                .build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
                .setMessageConverters(this.messageConverterSupplier.get())
                .build();
    }

    @AfterEach
    void tearDown() {
        reset(this.categoryService);
    }

    @DisplayName("Able to find a Category by it's name - ")
    @Test
    void getCategoryByName() throws Exception {

        // Given
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        Optional<Category> categoryOpt = Optional.of(this.category);
        given(this.categoryService.getCategoryByName(this.name))
                .willReturn(categoryOpt);

        DateMapper dateMapper = new DateMapper();
        OffsetDateTime createdDateDTO = dateMapper.timestampToOffsetDateTime(
                this.category.getCreatedDate());

        // When
        this.mockMvc.perform(get("/api/v1/categories/" + this.name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is((int)this.id)))
                .andExpect(jsonPath("$.createDate",
                        is(dateTimeFormatter.format(createdDateDTO))));
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