//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerControllerTest.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
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
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @DisplayName("Able to create a new Customer by Post method -")
    @Test
    void testCreateNewCustomerByPost() throws Exception {

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

    @DisplayName("Able to update a customer - ")
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

    @DisplayName("Able to patch a customer - ")
    @Test
    void testPatchCustomer() throws Exception {

        // Given
        long id = 7L;
        String firstname = "James";
        String lastname = "Bond";

        ICustomerMapper mapper = ICustomerMapper.INSTANCE;

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstname(firstname)
                .build();

        String json = new ObjectMapper().writeValueAsString(customerDTO);

        Customer customer = mapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        Customer expectedPatchedCustomer = Customer.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .build();

        CustomerDTO expectedPatchedCustomerDTO = mapper.customerToCustomerDto(
                expectedPatchedCustomer);

        given(this.customerService.patchCustomer(customer))
                .willReturn(expectedPatchedCustomer);

        String expectedUrl = expectedPatchedCustomerDTO.getCustomerUrl();

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                patch(ApiPaths.API_PATH_CUSTOMERS + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.firstname", is(firstname)))
                .andExpect(jsonPath("$.lastname", is(lastname)))
                .andExpect(jsonPath("$.customerUrl", is(expectedUrl)))
                .andReturn();

        // Then
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @DisplayName("Able to delete a customer - ")
    @Test
    void testDeleteCustomer() throws Exception {

        // Given
        long id = 7L;

        // When
        MvcResult mvcResult = this.mockMvc.perform(
                        delete(ApiPaths.API_PATH_CUSTOMERS + "/" + id))
                .andExpect(status().isAccepted())
                .andReturn();

        // Then
        then(this.customerService).should(times(1))
                .deleteCustomer(id);
        System.out.println(mvcResult.getResponse());
    }

}///:~