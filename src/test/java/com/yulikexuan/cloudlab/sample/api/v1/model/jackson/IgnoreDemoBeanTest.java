//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.IgnoreDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;


class IgnoreDemoBeanTest {

    private static String jsonString;
    private static ReadContext readContext;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUpAll() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        jsonString = objectMapper.writeValueAsString(new IgnoreDemoBean());
        readContext = JsonPath.parse(jsonString);
    }

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Only 'name' property is able to be serialized - ")
    @Test
    void testSerializedName() {

        // Then
        assertThat(readContext.read("$.name", String.class))
                .as("The name should be '%s'",
                        IgnoreDemoBean.NAME)
                .isEqualTo(IgnoreDemoBean.NAME);

        Assertions.assertThrows(PathNotFoundException.class,
                () ->  readContext.read("$.personId", Long.class));
    }

    @DisplayName("Only 'name' property is able to be deserialized - ")
    @Test
    void testDeSerializedName() throws IOException {

        // Given
        String newJsonString = "{\"personId\": 231, \"name\": \"Mary Parker\"}";

        // When
        IgnoreDemoBean ignoreDemoBean = objectMapper.readValue(newJsonString,
                IgnoreDemoBean.class);

        // Then
        assertThat(ignoreDemoBean.getName())
                .as("The name should be '%s'", "Mary Parker")
                .isEqualTo("Mary Parker");
        assertThat(ignoreDemoBean.getPersonId())
                .as("The personId property should still be %d",
                        IgnoreDemoBean.PERSON_ID)
                .isEqualTo(IgnoreDemoBean.PERSON_ID);
        assertThat(ignoreDemoBean.getPersonId())
                .as("The personId property should NOT be %d", 231)
                .isNotEqualTo(231L);
    }

}///:~