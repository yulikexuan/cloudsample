//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.GetterDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.GetterDemoBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class GetterDemoBeanTest {

    private GetterDemoBean bean;
    private ObjectMapper objectMapper;
    private DocumentContext docContext;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.bean = GetterDemoBean.builder().build();
        this.objectMapper = new ObjectMapper();
        this.json = this.objectMapper.writeValueAsString(this.bean);
        this.docContext = JsonPath.parse(this.json);
    }

    @DisplayName("Able to customize json key for property - ")
    @Test
    void testJsonKeysDefinedByJsonGetter() {

        // When
        int actualPersonId = this.docContext.read("$.Id");
        String actualPersonName = this.docContext.read("$.Name", String.class);

        // Then
        assertThat(actualPersonId)
                .as("Person ID should be '%s'", this.bean.getPersonId())
                .isEqualTo(this.bean.getPersonId());

        assertThat(actualPersonName)
                .as("Person Name should be '%s'", this.bean.getPersonName())
                .isEqualTo(this.bean.getPersonName());

        Assertions.assertThrows(PathNotFoundException.class,
                () -> this.docContext.read("$.personId"));
        Assertions.assertThrows(PathNotFoundException.class,
                () -> this.docContext.read("$.personName"));
    }

}///:~