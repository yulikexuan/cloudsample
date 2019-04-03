//: com.yulikexuan.cloudlab.sample.api.v1.controllers.CustomerController.java


package com.yulikexuan.cloudlab.sample.api.v1.controllers;


import com.yulikexuan.cloudlab.sample.api.v1.mappers.CustomerMapper;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import com.yulikexuan.cloudlab.sample.domain.services.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomerListDTO getListOfCustomers() {
        List<CustomerDTO> customerDTOs = this.customerService.getAllCustomers()
                .stream()
                .map(c -> {
                    CustomerDTO dto =
                            CustomerMapper.INSTANCE.customerToCustomerDto(c);
                    dto.setCustomerUrl("/api/v1/customers/" + c.getId());
                    return dto;
                })
                .collect(Collectors.toList());
        return CustomerListDTO.builder().customers(customerDTOs).build();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return this.customerService.getCustomerById(id)
                .map(c -> {
                    CustomerDTO dto =
                            CustomerMapper.INSTANCE.customerToCustomerDto(c);
                    dto.setCustomerUrl("/api/v1/custoemrs/" + c.getId());
                    return dto;
                })
                .orElseThrow(RuntimeException::new);
    }

}///:~