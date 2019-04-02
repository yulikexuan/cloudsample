//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize.DeserializeDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.deserialize;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class DeserializeDemoBeanTest {

    static final String JSON = "{\"personId\": 231, \"name\": \"Mary Parker\", " +
            "\"activeDate\":\"2017-09-26T11:12:30-0400\"}";

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Able to deserialize OffsetDateTime with formatted date string - ")
    void testDeserializeActiveDate() throws IOException {

        //Given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DeserializeDemoBean bean = objectMapper.readValue(
                JSON, DeserializeDemoBean.class);

        // When
        OffsetDateTime activeDate = bean.getActiveDate();
        int year = activeDate.getYear();
        int month = activeDate.getMonthValue();
        int day = activeDate.getDayOfMonth();
        int hour = activeDate.getHour();
        int min = activeDate.getMinute();
        int sec = activeDate.getSecond();
        String offsetId = activeDate.getOffset().getId();

        // Then
        assertThat(year).isEqualTo(2017);
        assertThat(month).isEqualTo(9);
        assertThat(day).isEqualTo(26);
        assertThat(hour).isEqualTo(11);
        assertThat(min).isEqualTo(12);
        assertThat(sec).isEqualTo(30);
        assertThat(offsetId).isEqualTo("-04:00");
    }

}///:~