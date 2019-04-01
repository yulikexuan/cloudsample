//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.SerializeDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.SerializeDemoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SerializeDemoBeanTest {

    private SerializeDemoBean bean;
    private ObjectMapper objectMapper;
    private DocumentContext documentContext;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.bean = SerializeDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.json = this.objectMapper.writeValueAsString(this.bean);
        this.documentContext = JsonPath.parse(this.json);
    }

    @DisplayName("Able to serialize date with DateTimeFormatter pattern - ")
    @Test
    void testSerializeActiveDateWithSimpleDateFormatPattern() {

        // Given
        String expectedDateTime = SerializeDemoBean.CustomDateSerializer
                .seralize(this.bean.getActiveDate());

        // When
        String actualDateTimeValue = this.documentContext.read(
                "$.activeDate", String.class);

        // Then
        assertThat(actualDateTimeValue)
                .as("Active date value should be serialized with " +
                        "DateTimeFormatter - ")
                .isEqualTo(expectedDateTime);
    }

}///:~