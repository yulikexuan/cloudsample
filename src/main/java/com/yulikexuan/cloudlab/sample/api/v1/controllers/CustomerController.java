//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerController.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.ApiPaths;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerListToCustomerListDtoMapper;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.services.ICustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.function.Supplier;


@RestController
@RequestMapping(ApiPaths.API_PATH_CUSTOMERS)
public class CustomerController {

    static final Supplier<ICustomerMapper> CUSTOMER_MAPPER_SUPPLIER =
            () -> ICustomerMapper.INSTANCE;

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerListDTO getListOfCustomers() {

        ICustomerListToCustomerListDtoMapper listMapper =
                Mappers.getMapper(ICustomerListToCustomerListDtoMapper.class);
        List<Customer> customers = this.customerService.getAllCustomers();
        return listMapper.customerListToCustomerListDto(customers);
    }

    @GetMapping(ApiPaths.API_PATH_VARIABLE_ID)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return this.customerService.getCustomerById(id)
                .map(CUSTOMER_MAPPER_SUPPLIER.get()::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDTO> createCustomer(
            @Valid @RequestBody CustomerDTO newCustomerDTO) {

        Customer newCustomer = CUSTOMER_MAPPER_SUPPLIER.get()
                .customerDtoToCustomer(newCustomerDTO);
        Customer savedCustoemr = this.customerService.saveCustomer(newCustomer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustoemr.getId())
                .toUri();
        CustomerDTO dto = CUSTOMER_MAPPER_SUPPLIER.get()
                .customerToCustomerDto(savedCustoemr);
        return ResponseEntity.created(location).body(dto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id,
                                      @RequestBody CustomerDTO customerDTO) {

        Customer customer = CUSTOMER_MAPPER_SUPPLIER.get()
                .customerDtoToCustomer(customerDTO);
        customer.setId(id);
        Customer savedCustoemr = this.customerService.saveCustomer(customer);

        return CUSTOMER_MAPPER_SUPPLIER.get().customerToCustomerDto(
                savedCustoemr);
    }

}///:~