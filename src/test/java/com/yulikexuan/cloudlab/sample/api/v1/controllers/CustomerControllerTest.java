//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerControllerTest.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.services.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private ICustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerController)
                .build();
    }

    @DisplayName("Able to get CustomerDTO by Id - ")
    @Test
    void getCustomerById() throws Exception {

        // Given
        long id = 7;
        String firstname = "Bill";
        String lastname = "Gates";
        Customer customer = Customer.builder().id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();

        Optional<Customer> customerOpt = Optional.of(customer);

        given(this.customerService.getCustomerById(id))
                .willReturn(customerOpt);

        String url = "/api/v1/customers/7";

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                        get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(firstname)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.customerUrl",
                        is(url)))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @DisplayName("Able to get all customers - ")
    @Test
    void getListOfCustomers() throws Exception {

        // Given
        long id1 = 7L;
        String firstname1 = "Bill";
        String lastname1 = "Gates";
        long id2 = 17L;
        String firstname2 = "Steve";
        String lastname2 = "Jobs";

        List<Customer> allCustomers = List.of(
                Customer.builder().id(id1)
                        .firstname(firstname1)
                        .lastname(lastname1)
                        .build(),
                Customer.builder()
                        .id(id2)
                        .firstname(firstname2)
                        .lastname(lastname2)
                        .build());

        given(this.customerService.getAllCustomers())
                .willReturn(allCustomers);

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                        get(ApiPaths.API_PATH_CUSTOMERS)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.customers.length()",
                        is(allCustomers.size())))
                .andExpect(jsonPath("$.customers[0].customerUrl",
                        is(ApiPaths.API_PATH_CUSTOMERS + "/" + id1)))
                .andExpect(jsonPath("$.customers[1].customerUrl",
                        is(ApiPaths.API_PATH_CUSTOMERS + "/" + id2)))
                .andReturn();


        // Then
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}///:~