//: com.yulikexuan.cloudlab.sample.api.v1.model.ExtendableBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ExtendableBeanTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void testSerializing() throws Exception {

        // Given
        ExtendableBean bean = new ExtendableBean();
        bean.setName("Yul");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");
        bean.add("attr3", "val3");

        // When
        String jsonString = new ObjectMapper().writeValueAsString(bean);

        // Then
        System.out.println(jsonString);

        /*
         * Without @JsonAnyGetter:
         * {"name":"Yul","properties":{"attr2":"val2","attr1":"val1","attr3":"val3"}}
         */

        /*
         * With @JsonAnyGetter:
         * {"name":"Yul","properties":{"attr2":"val2","attr1":"val1","attr3":"val3"}}
         */
    }

}///:~