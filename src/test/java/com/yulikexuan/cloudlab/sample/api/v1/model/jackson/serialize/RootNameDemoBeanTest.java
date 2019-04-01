//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.RootNameDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import com.yulikexuan.cloudlab.sample.api.v1.model.jackson.serialize.RootNameDemoBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RootNameDemoBeanTest {

    private RootNameDemoBean bean;
    
    @BeforeEach
    void setUp() {
        this.bean = RootNameDemoBean.builder().build();
    }

    @Test
    void testRootNodeName() throws JsonProcessingException {

        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .writeValueAsString(this.bean);
        // When
        assertThat(JsonPath.isPathDefinite("$.user")).isEqualTo(true);
    }

}///:~