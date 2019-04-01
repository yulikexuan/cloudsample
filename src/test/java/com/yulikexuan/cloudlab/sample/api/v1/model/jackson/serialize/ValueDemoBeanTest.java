//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.ValueDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.ValueDemoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ValueDemoBeanTest {

    private ValueDemoBean bean;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.bean = ValueDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void testJsonStringIsDefinedByValueDemoBean() throws JsonProcessingException {

        // Given
        String expectedJsonString = this.bean.toJson();

        // When
        String json = this.objectMapper.writeValueAsString(this.bean);
        System.out.println(json);
        // Then
        assertThat(json)
                .as("The serialized json string should be '%s'",
                        expectedJsonString)
                .isEqualTo("\"" + expectedJsonString + "\"");
    }

}///:~