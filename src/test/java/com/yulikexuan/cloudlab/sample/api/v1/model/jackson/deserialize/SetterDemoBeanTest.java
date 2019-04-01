//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.SetterDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class SetterDemoBeanTest {

    static final String JSON_VALUE = "{\"Id\": 231, \"Name\": \"Mary Parker\"}";

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Json key for personId can be changed - ")
    @Test
    void testJsonKeyForPersonId() throws IOException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentContext documentContext = JsonPath.parse(JSON_VALUE);
        int expectedPersonId = documentContext.read("$.Id");
        String expectedPersonName = documentContext.read("$.Name",
                String.class);

        // When
        SetterDemoBean bean = objectMapper.readValue(
                JSON_VALUE, SetterDemoBean.class);

        // Then
        assertThat(bean.getPersonId())
                .as("Person Id should be %d", expectedPersonId)
                .isEqualTo(expectedPersonId);
        assertThat(bean.getName()).as("Person's name should be %s",
                expectedPersonName);
    }

    @DisplayName("Person name will be set to empty string if no name in json - ")
    @Test
    void testJsonKeyForPersonName() throws IOException {

        // Given
        String json =  "{\"Id\": 231, \"Name\": null}";
        ObjectMapper objectMapper = new ObjectMapper();

        // When
        SetterDemoBean bean = objectMapper.readValue(
                json, SetterDemoBean.class);

        // Then
        assertThat(bean.getName())
                .as("Person's name should be empty string")
                .isEmpty();
    }

}///:~