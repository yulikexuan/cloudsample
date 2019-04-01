//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.AutoDetectDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class AutoDetectDemoBeanTest {

    private ObjectMapper objectMapper;

    private AutoDetectDemoBean bean;

    private String json;

    @BeforeEach()
    void setUp() throws JsonProcessingException {
        this.bean = AutoDetectDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.json = this.objectMapper.writeValueAsString(this.bean);
    }

    @Test
    @DisplayName("Able to serialize private fields - ")
    void testSerializePrivateFields() throws IOException {

        // When
        String json = this.objectMapper.writeValueAsString(this.bean);
        System.out.println(json);
        DocumentContext dc = JsonPath.parse(json);

        // Then
        assertThat(dc.read("$.personId", Long.class))
                .as("PersonId should be '%d'", 123)
                .isEqualTo(123);
        assertThat(dc.read("$.name", String.class))
                .as("Name should be '%s'", "James Clark")
                .isEqualTo("James Clark");

        String newJson = "{\"personId\":7,\"name\":\"Bill Gates\"}";
        AutoDetectDemoBean newBean = this.objectMapper.readValue(newJson, AutoDetectDemoBean.class);
        System.out.println(newBean);
    }

}///:~