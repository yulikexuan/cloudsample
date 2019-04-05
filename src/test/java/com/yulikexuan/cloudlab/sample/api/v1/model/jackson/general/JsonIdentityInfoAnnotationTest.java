//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.JsonIdentityInfoAnnotationTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class JsonIdentityInfoAnnotationTest {


    @BeforeEach
    void setUp() {
    }

    @DisplayName("Able to include the back reference object when serializing - ")
    @Test
    void testIncludeTheBackReferenceObject() throws JsonProcessingException {

        // Given
        IdentityInfoEmployeeDemoBean employee = IdentityInfoEmployeeDemoBean
                .builder().build();

        IdentityInfoEmployeeDemoBean yul = IdentityInfoEmployeeDemoBean.builder()
                .personId(27L)
                .name("Yul")
                .build();

        IdentityInfoManagerDemoBean manager = IdentityInfoManagerDemoBean.builder()
                .employee(employee)
                .employee(yul)
                .build();

        employee.setManager(manager);
        yul.setManager(manager);

        ObjectMapper objectMapper = new ObjectMapper();

        // When
        String managerJson = objectMapper.writeValueAsString(manager);
        String employeeJson = objectMapper.writeValueAsString(employee);
        String yulJson = objectMapper.writeValueAsString(yul);

        // Then
        System.out.println(managerJson);
        System.out.println(employeeJson);
        System.out.println(yulJson);
    }

}///:~