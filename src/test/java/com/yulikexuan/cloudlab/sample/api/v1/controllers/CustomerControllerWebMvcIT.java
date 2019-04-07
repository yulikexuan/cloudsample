//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerControllerWebMvcIT.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.services.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CustomerController.class)
@ExtendWith(MockitoExtension.class)
class CustomerControllerWebMvcIT {

    static final String API_BASE_PATH = "/api/v1/customers";
    static final String API_PATH_TEMPLATE = "/api/v1/customers/%d";

    static final Function<Long, String> URL_BUILDER =
            (id) -> String.format(API_PATH_TEMPLATE, id);

    @MockBean
    private ICustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        reset(this.customerService);
    }

    @Test
    @DisplayName("Able to get a CustomerDTO by Id - ")
    void getCustomerById() throws Exception {

        // Given
        long id = 7L;
        String firstname = "Steve";
        String lastname = "Jobs";
        Customer customer = Customer.builder().id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();

        given(this.customerService.getCustomerById(id))
                .willReturn(Optional.of(customer));

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                        get(URL_BUILDER.apply(id)).accept(
                                MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(firstname)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.customerUrl",
                        is(URL_BUILDER.apply(id))))
                .andReturn();

    }

    @Test
    void getListOfCustomers() throws Exception {

        // Given
        long id1 = 7L;
        long id2 = 17L;
        String firstname1 = "Steve";
        String firstname2 = "Bill";
        String lastname1 = "Jobs";
        String lastname2 = "Getes";
        List<Customer> customers = List.of(
                Customer.builder().id(id1).firstname(firstname1)
                        .lastname(lastname1).build(),
                Customer.builder().id(id2).firstname(firstname2)
                        .lastname(lastname2).build());

        given(this.customerService.getAllCustomers())
                .willReturn(customers);

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                        get(API_BASE_PATH).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.customers.length()", is(2)))
                .andExpect(jsonPath("$.customers[0].firstname", is(firstname1)))
                .andExpect(jsonPath("$.customers[0].lastname", is(lastname1)))
                .andReturn();

        CustomerListDTO dtos = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(),
                CustomerListDTO.class);

        // Then
//        assertThat(dtos.getCustomers()).as("Should have two customers.")
//                .isEqualTo(customers);
    }

    @DisplayName("Able to create new Customer - ")
    @Test
    void testCreateNewCustomer() throws Exception {

        // Given
        long id = 7L;
        String firstname = "James";
        String lastname = "Bond";

        ICustomerMapper mapper = ICustomerMapper.INSTANCE;

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        String newJson = new ObjectMapper().writeValueAsString(customerDTO);

        Customer customer = mapper.customerDtoToCustomer(customerDTO);

        Customer expectedSavedCustomer = Customer.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();

        CustomerDTO expectedSavedCustomerDTO = mapper.customerToCustomerDto(
                expectedSavedCustomer);

        given(this.customerService.saveCustomer(customer))
                .willReturn(expectedSavedCustomer);

        String expectedUrl = expectedSavedCustomerDTO.getCustomerUrl();

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                post(ApiPaths.API_PATH_CUSTOMERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(firstname)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.customerUrl", is(expectedUrl)))
                .andReturn();

        // Then
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Able to update a Customer - ")
    @Test
    void testUpdateCustomer() throws Exception {

        // Given
        long id = 7L;
        String firstname = "James";
        String lastname = "Bond";

        ICustomerMapper mapper = ICustomerMapper.INSTANCE;

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(firstname)
                .lastname(lastname)
                .build();

        String json = new ObjectMapper().writeValueAsString(customerDTO);

        Customer customer = mapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        Customer expectedUpdatedCustomer = Customer.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();

        CustomerDTO expectedUpdatedCustomerDTO = mapper.customerToCustomerDto(
                expectedUpdatedCustomer);

        given(this.customerService.saveCustomer(customer))
                .willReturn(expectedUpdatedCustomer);

        String expectedUrl = expectedUpdatedCustomerDTO.getCustomerUrl();

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                put(ApiPaths.API_PATH_CUSTOMERS + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(firstname)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.customerUrl", is(expectedUrl)))
                .andReturn();

        // Then
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}///:~