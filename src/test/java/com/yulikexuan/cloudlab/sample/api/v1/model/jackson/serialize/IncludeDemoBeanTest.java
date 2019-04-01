//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.IncludeDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.IncludeDemoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class IncludeDemoBeanTest {

    private IncludeDemoBean bean;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.bean = IncludeDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
    }

    @DisplayName("Null fields are not included in json - ")
    @Test
    void testNullFieldsAreNotIncludedInJson() throws JsonProcessingException {

        // When
        String json = this.objectMapper.writeValueAsString(this.bean);

        // Then
        assertThat(json).as("Should not include '%s'", "name")
                .doesNotContain("name");
        assertThat(json).as("Should not include '%s'", "sinCode")
                .doesNotContain("sinCode");
    }

}///:~