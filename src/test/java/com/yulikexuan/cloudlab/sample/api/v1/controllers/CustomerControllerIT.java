//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerControllerIT.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Able to get all pre-loaded customers - ")
    void testAllCustomers() {

        // When
        CustomerListDTO customerListDTO = this.testRestTemplate
                .getForObject(ApiPaths.API_PATH_CUSTOMERS, CustomerListDTO.class);

        // Then
        assertThat(customerListDTO.getCustomers().size())
                .as("Should have 3 customers.")
                .isEqualTo(3);

    }

}///:~