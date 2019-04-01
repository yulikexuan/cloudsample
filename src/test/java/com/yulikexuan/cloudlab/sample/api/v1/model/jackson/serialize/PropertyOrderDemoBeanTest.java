//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.PropertyOrderDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.PropertyOrderDemoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PropertyOrderDemoBeanTest {

    private ObjectMapper objectMapper;
    private DocumentContext docContext;
    private PropertyOrderDemoBean bean;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.bean = PropertyOrderDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.json = this.objectMapper.writeValueAsString(this.bean);
        this.docContext = JsonPath.parse(this.json);
    }

    @Test
    void name() {

        // When
        int indexOfEmailProperty = this.json.indexOf("email");
        int indexOfNameProperty = this.json.indexOf("name");
        int indexOfPersonId = this.json.indexOf("personId");

        // Then
        assertThat(indexOfEmailProperty)
                .as("Email property should be in front of name")
                .isLessThan(indexOfNameProperty);
        assertThat(indexOfNameProperty)
                .as("Name property should be in front of personId")
                .isLessThan(indexOfPersonId);

        System.out.println(this.json);
    }
}///:~