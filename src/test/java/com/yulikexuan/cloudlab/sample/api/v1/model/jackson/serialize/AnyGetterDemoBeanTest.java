//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.AnyGetterDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.Gender;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.AnyGetterDemoBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class AnyGetterDemoBeanTest {

    private AnyGetterDemoBean bean;
    private ObjectMapper objectMapper;
    private DocumentContext documentContext;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.bean = AnyGetterDemoBean.builder()
                .property("email", "likexuan1996@yahoo.com")
                .property("gender", Gender.MALE.toString())
                .build();
        this.objectMapper = new ObjectMapper();
        this.json = this.objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(this.bean);
        this.documentContext = JsonPath.parse(this.json);
        System.out.println(this.json);
    }

    @DisplayName("Should not have json key for properties map - ")
    @Test
    void testNoJsonkeyForPropertiesMap() {

        // When
        String actualEmail = this.documentContext.read("$.email",
                String.class);
        String actualGender = this.documentContext
                .read("$.gender", String.class);
        String expectGender = this.bean.getProperties().get("gender");

        // Then
        assertThat(actualEmail)
                .as("The email should be '%s'",
                        this.bean.getProperties().get("email"))
                .isEqualTo(this.bean.getProperties().get("email"));

        assertThat(actualGender)
                .as("The gender should be '%s'", expectGender)
                .isEqualTo(expectGender);

        Assertions.assertThrows(PathNotFoundException.class,
                () -> this.documentContext.read("$.properties", Map.class));

    }

}///:~