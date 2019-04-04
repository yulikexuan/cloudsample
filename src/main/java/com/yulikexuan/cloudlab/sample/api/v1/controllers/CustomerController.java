//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerController.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerListToCustomerListDtoMapper;
import com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import com.yulikexuan.cloudlab.sample.domain.services.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final ICustomerMapper customerMapper;
    private final ICustomerListToCustomerListDtoMapper customerListMapper;
    private final ICustomerService customerService;

    public CustomerController(ICustomerMapper customerMapper,
                              ICustomerListToCustomerListDtoMapper customerListMapper,
                              ICustomerService customerService) {

        this.customerMapper = customerMapper;
        this.customerListMapper = customerListMapper;
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerListDTO getListOfCustomers() {
        return customerListMapper.customerListToCustomerListDto(
                this.customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return this.customerService.getCustomerById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

}///:~