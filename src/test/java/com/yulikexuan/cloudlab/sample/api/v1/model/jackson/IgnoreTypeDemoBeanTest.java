//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnoreTypeDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class IgnoreTypeDemoBeanTest {

    private String jsonString;
    private ReadContext jsonContext;
    private ObjectMapper objectMapper;
    private IgnoreTypeDemoBean bean;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.bean = IgnoreTypeDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.jsonString = objectMapper.writeValueAsString(this.bean);
        this.jsonContext = JsonPath.parse(jsonString);
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Address should not be serialized - ")
    @Test
    void testNotSerializeAddress() {

        //Given
        long expectedPersonId = this.bean.getPersonId();
        String expectedName = this.bean.getName();

        // When
        int actualPersonId = this.jsonContext.read("$.personId");
        String actualName = this.jsonContext.read("$.name");

        // Then
        assertThat(actualPersonId)
                .as("The personId should be %d", actualPersonId)
                .isEqualTo(actualPersonId);
        assertThat(actualName)
                .as("The name should be %s", actualName)
                .isEqualTo(actualName);
        Assertions.assertThrows(PathNotFoundException.class,
                () -> this.jsonContext.read("$.address"));
    }

    @Test
    @DisplayName("Address should not be deserialzed - ")
    void testNotDeserializeAddress() throws IOException {

        // Given
        String jsonString = "{\"personId\": 123, \"name\": \"Mary Parker\", " +
                "\"address\": {\"doorNumber\": \"123\", " +
                "\"streetName\": \"Phase 1\", \"pincode\": \"123456\", " +
                "\"city\": \"New York\"}}";

        // When
        IgnoreTypeDemoBean beanFromJson = this.objectMapper.readValue(
                jsonString, IgnoreTypeDemoBean.class);

        // Then
        assertThat(beanFromJson.getAddress())
                .as("Adress should still be the default value")
                .isEqualTo(this.bean.getAddress());
    }

}///:~