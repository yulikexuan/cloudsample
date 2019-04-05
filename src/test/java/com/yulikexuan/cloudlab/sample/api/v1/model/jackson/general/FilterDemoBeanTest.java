//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.FilterDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class FilterDemoBeanTest {


    @BeforeEach
    void setUp() {
    }

    @DisplayName("Able to filter all properties except name - ")
    @Test
    void testSerializingWithJsonFilter() throws JsonProcessingException {

        // Given
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(
                "customFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        // When
        String json = new ObjectMapper().writer(filterProvider)
                .writeValueAsString(FilterDemoBean.builder().build());

        DocumentContext documentContext = JsonPath.parse(json);
        String name = documentContext.read("$.name", String.class);

        // Then
        assertThat(name).as("The name of the bean is %s", name)
                .isEqualTo("James Bond");
        Assertions.assertThrows(PathNotFoundException.class,
                () -> documentContext.read("$.persionId"));
        Assertions.assertThrows(PathNotFoundException.class,
                () -> documentContext.read("$.gender"));

    }

}///:~