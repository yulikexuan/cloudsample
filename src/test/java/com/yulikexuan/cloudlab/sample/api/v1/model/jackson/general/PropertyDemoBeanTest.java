//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.PropertyDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


class PropertyDemoBeanTest {

    static final String JSON = "{\"UserName\":\"stevejobs\"," +
            "\"Password\":\"ddb2jf83-991x-4907-b7fb-z42f1ad9ad89\"}";

    private PropertyDemoBean bean;
    private ObjectMapper objectMapper;
    private DocumentContext documentContext;
    private String data;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.objectMapper = new ObjectMapper();
        this.bean = PropertyDemoBean.builder().build();
        this.data = this.objectMapper.writeValueAsString(this.bean);
        this.documentContext = JsonPath.parse(this.data);
    }

    @Test
    @DisplayName("Able to customize data keys - ")
    void testSerializedKeys() {

        // When
        String username = this.documentContext.read("$.UserName",
                String.class);
        String password = this.documentContext.read("$.Password",
                String.class);

        // Then
        assertThat(username)
                .as("Username should be %s", this.bean.getUsername())
                .isEqualTo(this.bean.getUsername());
        assertThat(password)
                .as("Password should be %s", this.bean.getPassword())
                .isEqualTo(this.bean.getPassword());
    }

    @Test
    void testDeserializedKeys() throws IOException {

        // When
        PropertyDemoBean bean = this.objectMapper.readValue(JSON,
                PropertyDemoBean.class);

        // Then
        assertThat(bean.getUsername())
                .as("Username should be %s", "stevejobs")
                .isEqualTo("stevejobs");
        assertThat(bean.getPassword())
                .as("Password should be %s",
                        "ddb2jf83-991x-4907-b7fb-z42f1ad9ad89")
                .isEqualTo("ddb2jf83-991x-4907-b7fb-z42f1ad9ad89");
    }

}///:~