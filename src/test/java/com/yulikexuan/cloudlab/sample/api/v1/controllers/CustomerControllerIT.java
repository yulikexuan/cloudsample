//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerControllerIT.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

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

    @DisplayName("Able to create new Customer - ")
    @Test
    void testCreateNewCustomer() {

        // Given
        long id = 7L;
        String firstname = "James";
        String lastname = "Bond";

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        // When
        CustomerDTO savedCustomerDTO = this.testRestTemplate.postForEntity(
                ApiPaths.API_PATH_CUSTOMERS,
                customerDTO, CustomerDTO.class).getBody();

        String url = savedCustomerDTO.getCustomerUrl();

        customerDTO.setCustomerUrl(url);

        ///api/v1/customers/4

        // Then
        assertThat(savedCustomerDTO)
                .as("The saved CustomerDTO should be %s", customerDTO)
                .isEqualTo(customerDTO);
        System.out.println(url);
    }

    @DisplayName("Able to update new Customer - ")
    @Test
    void testUpdateCustomer() {

        // Given
        long id = 3L;
        String firstname = "James";
        String lastname = "Bond";

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        String url = ApiPaths.API_PATH_CUSTOMERS + "/" + id;

        URI uri = URI.create(url);

        RequestEntity requestEntity = RequestEntity.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerDTO);

        // When
        final ResponseEntity<CustomerDTO> response =
                this.testRestTemplate.exchange(requestEntity, CustomerDTO.class);

        CustomerDTO updatedCustomerDTO = response.getBody();

        HttpStatus httpStatus = response.getStatusCode();

        String updatedUrl = updatedCustomerDTO.getCustomerUrl();


        // : /api/v1/customers/3

        // Then
        customerDTO.setCustomerUrl(url);
        assertThat(updatedCustomerDTO)
                .as("The updated Customer should be %s", customerDTO)
                .isEqualTo(customerDTO);
        System.out.println(updatedUrl);
    }

}///:~