//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.CreatorDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class CreatorDemoBeanTest {

    static final String JSON = "{\"Id\": 17, \"Name\": \"Bill Gates\"}";

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Able to initialize immutable bean with json string - ")
    void testInitializeCreatorDemoBeanWithJsonString() throws IOException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();

        // When
        CreatorDemoBean bean = objectMapper.readValue(JSON, CreatorDemoBean.class);
        long actualId = bean.getPersonId();
        String actualName = bean.getName();

        // Then
        assertThat(actualId).as("Person Id should be %d", 17)
                .isEqualTo(17);
        assertThat(actualName).as("Person Id should be %s",
                "Bill Gates")
                .isEqualTo("Bill Gates");
    }

}///:~