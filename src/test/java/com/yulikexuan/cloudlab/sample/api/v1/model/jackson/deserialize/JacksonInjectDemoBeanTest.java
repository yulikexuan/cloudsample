//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.JacksonInjectDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class JacksonInjectDemoBeanTest {

    static final String JSON = "{\"name\": \"Mary Parker\"}";

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Person Id's value should not be read from JSON - ")
    void testPersonIdShouldBeInjected() throws IOException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        InjectableValues injectableValues = new InjectableValues.Std()
                .addValue(long.class, 17L);
        JacksonInjectDemoBean bean = new ObjectMapper()
                .reader(injectableValues)
                .forType(JacksonInjectDemoBean.class)
                .readValue(JSON);

        // When

        // Then
        assertThat(bean.getPersonId()).as("PersonId should be %d", 17)
                .isEqualTo(17);
    }

}///:~