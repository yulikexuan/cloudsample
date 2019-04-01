//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnorePropertiesDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class IgnorePropertiesDemoBeanTest {

    private String jsonString;
    private ReadContext readContext;
    private ObjectMapper objectMapper;

    private Random random;

    private long id;
    private String name;
    private Gender gender;

    private IgnorePropertiesDemoBean bean;

    @BeforeEach
    void setUpAll() throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.random = new Random(System.currentTimeMillis());
        this.id = this.random.nextInt(10) + 1;
        this.name = UUID.randomUUID().toString();
        this.gender = this.random.nextBoolean() ?
                Gender.FEMALE : Gender.MALE;
        this.bean = IgnorePropertiesDemoBean.builder()
                .id(this.id)
                .name(this.name)
                .gender(this.gender)
                .build();
        this.jsonString = objectMapper.writeValueAsString(this.bean);
        this.readContext = JsonPath.parse(jsonString);
    }

    @AfterEach
    void tearDown() {
        this.objectMapper = null;
    }

    @DisplayName("Only 'name' property is able to be serialized - ")
    @Test
    void testSerializedProperties() {

        // when
        String actualName = this.readContext.read("$.name", String.class);

        // Then
        assertThat(actualName)
                .as("The name should be '%s'", this.name)
                .isEqualTo(this.name);
        Assertions.assertThrows(PathNotFoundException.class,
                () -> readContext.read("$.gender", Gender.class));
        Assertions.assertThrows(PathNotFoundException.class,
                () -> readContext.read("$.id"));
    }

    @Test
    @DisplayName("Only 'name' property is able to be de-serialized - ")
    void testDeserializedProperties() throws IOException {

        // Given
        String name = "Mary Parker";
        String jsonString = "{\"id\": 231, \"name\": \"Mary Parker\", " +
                "\"gender\": \"MALE\"}";

        // When
        IgnorePropertiesDemoBean beanFromJson = this.objectMapper.readValue(
                jsonString, IgnorePropertiesDemoBean.class);


        // Then
        assertThat(beanFromJson.getName())
                .as("The name property should be '%s'", name)
                .isEqualTo(name);
        assertThat(beanFromJson.getId())
                .as("Id property should be null")
                .isNull();
        assertThat(beanFromJson.getGender())
                .as("Gender property should be null")
                .isNull();
    }

}///:~