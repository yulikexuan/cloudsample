//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryControllerWebMvcIT.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.DateMapper;
import com.yulikexuan.cloudlab.sample.domain.model.Category;
import com.yulikexuan.cloudlab.sample.domain.services.ICategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
 * Using this annotation will disable full auto-configuration and instead apply
 * only configuration relevant to MVC tests
 * (i.e. @Controller, @ControllerAdvice, @JsonComponent,
 * Converter/GenericConverter, Filter, WebMvcConfigurer and
 * HandlerMethodArgumentResolver beans
 * BUT NOT @Component, @Service or @Repository beans).
 * By default, tests annotated with @WebMvcTest will also auto-configure Spring
 * Security and MockMvc (include support for HtmlUnit WebClient and
 * Selenium WebDriver). For more fine-grained control of MockMVC the
 * @AutoConfigureMockMvc annotation can be used.
 *
 * Typically @WebMvcTest is used in combination with @MockBean or @Import to
 * create any collaborators required by your @Controller beans.
 */
@WebMvcTest(CategoryController.class)
@ExtendWith(MockitoExtension.class)
class CategoryControllerWebMvcIT {

    /*
     * When @MockBean is used on a field, as well as being registered in the
     * application context, the mock will also be injected into the field.
     * We use @MockBean to create and inject a mock for the ICategoryService
     * using Mockito
     * (if don’t do this the application context cannot start)
     */
    @MockBean
    private ICategoryService categoryService;

    private Category category;
    private long id;
    private String name;

    @Autowired
    private MockMvc mockMvc;

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
        MvcResult mvcResult = this.mockMvc.perform(
                        get("/api/v1/categories/" + this.name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is((int)this.id)))
                .andExpect(jsonPath("$.createDate",
                        is(dateTimeFormatter.format(createdDateDTO))))
                .andReturn();

        // Then
        System.out.printf("%n>>>>>>> The response is: %n    '%s'%n",
                mvcResult.getResponse().getContentAsString());
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