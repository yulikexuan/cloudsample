//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.FormatDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


class FormatDemoBeanTest {

    static final DateTimeFormatter ACTIVE_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    private FormatDemoBean bean;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.bean = FormatDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @DisplayName("Able to serialize date with Json Format - ")
    @Test
    void testJsonFormatForSerializing() throws JsonProcessingException {

        // Given
        String expectedActiveDateJson = "2019-04-02T09:24:29-0400";
        OffsetDateTime expectedActiveDate = OffsetDateTime.parse(
                expectedActiveDateJson, ACTIVE_DATE_FORMATTER);
        this.bean.setActiveDate(expectedActiveDate);
        String json = this.objectMapper.writeValueAsString(this.bean);
        DocumentContext documentContext = JsonPath.parse(json);

        // When
        System.out.println(json);
        String activeDate = documentContext.read("$.ActiveDate");

        // Then
        assertThat(activeDate)
                .as("Serialized ActiveDate should be '%s'",
                        expectedActiveDateJson)
                .isEqualTo(expectedActiveDateJson);
    }

//    NOT WORKS for deserialization
//
//    @DisplayName("Able to deserialize date with Json Format Annotation - ")
//    @Test
//    void testJsonFormatForDeserializing() throws IOException {
//
//        // Given
//        String json = "{\"Id\":7,\"Name\":\"Bill Gates\"," +
//                "\"ActiveDate\":\"2019-04-02T10:25:30-0400\"}";
//        OffsetDateTime expectedActiveDate = OffsetDateTime.parse(
//                "2019-04-02T10:25:30-0400", ACTIVE_DATE_FORMATTER);
//
//        // When
//        FormatDemoBean bean = this.objectMapper.readValue(json,
//                FormatDemoBean.class);
//        String actualActiveDateString = bean.getActiveDate().format(
//                ACTIVE_DATE_FORMATTER);
//
//        // Then
//        assertThat(actualActiveDateString)
//                .as("Active Date should be '%s'",
//                        expectedActiveDate)
//                .isEqualTo(expectedActiveDate);
//    }

}///:~