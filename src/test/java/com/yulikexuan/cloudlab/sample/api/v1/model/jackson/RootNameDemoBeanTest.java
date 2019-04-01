//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.RootNameDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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