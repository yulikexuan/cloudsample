//: com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general.ManagedAndBackReferenceDemoBeanTest.java


package com.yulikexuan.cloudlab.sample.api.v1.model.jackson.general;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ManagedAndBackReferenceDemoBeanTest {

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Able to resolve cyclic reference between beans - ")
    @Test
    void testCyclicReference() throws JsonProcessingException {

        // Given
        BackReferenceDemoBean manager = BackReferenceDemoBean.builder()
                .personId(17L)
                .name("Bill Gates")
                .build();
        ManagedReferenceDemoBean employee = ManagedReferenceDemoBean.builder()
                .backManager(manager)
                .build();
        manager.addEmployees(employee);

        ObjectMapper objectMapper = new ObjectMapper();

        // When
        String managerJson = objectMapper.writeValueAsString(manager);
        String employeeJson = objectMapper.writeValueAsString(employee);

        System.out.println(managerJson);
        System.out.println(employeeJson);
        System.out.println(manager.getEmployees());
    }

}///:~