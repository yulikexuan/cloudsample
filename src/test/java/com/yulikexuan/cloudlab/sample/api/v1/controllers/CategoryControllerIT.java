//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CategoryControllerIT.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CategoryListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Java6Assertions.assertThat;


/*
 * @SpringBootTest annotation, which can be used as an alternative to the
 * standard spring-test @ContextConfiguration annotation when you need Spring
 * Boot features.
 * The annotation works by creating the ApplicationContext used in your tests
 * through SpringApplication.
 *
 * In addition to @SpringBootTest a number of other annotations are also
 * provided for testing more specific slices of an application.
 *
 * If you are using JUnit 5, there’s no need to add the equivalent
 * @ExtendWith(SpringExtension) as @SpringBootTest and the other @…Test
 * annotations are already annotated with it.
 *
 * By default, @SpringBootTest will not start a server.
 * You can use the webEnvironment attribute of @SpringBootTest to further
 * refine how your tests run:
 *   - MOCK(Default) : Loads a web ApplicationContext and provides a mock web
 *     environment. Embedded servers are not started when using this annotation.
 *     If a web environment is not available on your classpath, this mode
 *     transparently falls back to creating a regular non-web ApplicationContext.
 *     It can be used in conjunction with @AutoConfigureMockMvc or
 *     @AutoConfigureWebTestClient for mock-based testing of your web
 *     application.
 *   - RANDOM_PORT: Loads a WebServerApplicationContext and provides a real
 *     web environment. Embedded servers are started and listen on a random port.
 *     @SpringBootTest with webEnvironment = WebEnvironment.RANDOM_PORT will
 *     also start the management server on a separate random port if your
 *     application uses a different port for the management server.
 *   - DEFINED_PORT: Loads a WebServerApplicationContext and provides a real web
 *     environment. Embedded servers are started and listen on a defined port
 *     (from your application.properties) or on the default port of 8080.
 *   - NONE: Loads an ApplicationContext by using SpringApplication but does
 *     not provide any web environment (mock or otherwise).
 *
 * If your test is @Transactional, it rolls back the transaction at the end of
 * each test method by default.
 * However, as using this arrangement with either RANDOM_PORT or DEFINED_PORT
 * implicitly provides a real servlet environment, the HTTP client and server
 * run in separate threads and, thus, in separate transactions.
 * Any transaction initiated on the server does not roll back in this case.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("Able to get all pre-loaded categories - ")
    @Test
    void testListOfCategory() {

        // When
        CategoryListDTO categoryListDTO = this.testRestTemplate.getForObject(
                "/api/v1/categories", CategoryListDTO.class);

        // Then
        assertThat(categoryListDTO.getCategories().size()).isEqualTo(5);
        categoryListDTO.getCategories().stream()
                .forEach(c -> System.out.println(c));
    }

    @DisplayName("Able to get all pre-loaded categories - ")
    @Test
    void testListOfCategoryEntity() {

        // When
        ResponseEntity<CategoryListDTO> entity =
                this.testRestTemplate.getForEntity("/api/v1/categories",
                        CategoryListDTO.class);

        // Then
        assertThat(entity.getBody().getCategories().size()).isEqualTo(5);
    }

}///:~