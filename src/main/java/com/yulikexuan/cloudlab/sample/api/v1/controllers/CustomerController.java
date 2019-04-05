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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(ApiPaths.API_PATH_CUSTOMERS)
public class CustomerController {

    private final ICustomerService customerService;

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
                .map(ICustomerMapper.INSTANCE::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

}///:~