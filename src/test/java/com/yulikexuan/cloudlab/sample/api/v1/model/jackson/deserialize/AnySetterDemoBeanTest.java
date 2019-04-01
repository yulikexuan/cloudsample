//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.AnySetterDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class AnySetterDemoBeanTest {

    static final String JSON = "{\"personId\": 231, " +
            "\"personName\": \"Mary Parker\", " +
            "\"emailId\": \"mary@gmail.com\", \"gender\": \"female\"}";

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Able to use Key-Value pairs in json string to desrialize Properties Map - ")
    @Test
    void testDeserializePropertiesByKeyValuePairs() throws IOException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        AnySetterDemoBean bean = objectMapper.readValue(JSON,
                AnySetterDemoBean.class);

        // When
        Map<String, String> properties = bean.getProperties();
        String email = properties.get("emailId");
        String gender = properties.get("gender");

        // Then
        assertThat(email).as("Email Id should be %s", email)
                .isEqualTo("mary@gmail.com");
        assertThat(gender).as("Gender should be %s", gender)
                .isEqualTo("female");
    }

}///:~