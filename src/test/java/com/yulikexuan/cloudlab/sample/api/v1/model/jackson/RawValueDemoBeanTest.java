//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.RawValueDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RawValueDemoBeanTest {

    private RawValueDemoBean bean;

    @BeforeEach
    void setUp() {
        this.bean = new RawValueDemoBean();
    }

    @Test
    void name() throws JsonProcessingException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();

        // When
        String json = objectMapper.writeValueAsString(this.bean);

        // Then
        System.out.println(json);
    }

}///:~