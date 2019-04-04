//: com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerListToCustomerListDtoMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerListDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ICustomerListToCustomerListDtoMapper {

    default CustomerListDTO customerListToCustomerListDto(
            List<Customer> customers) {

        List<CustomerDTO> dtos = Optional.ofNullable(customers)
                .map(customerList -> customerList.stream()
                        .map(ICustomerMapper.INSTANCE::customerToCustomerDto)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        return CustomerListDTO.builder().customers(dtos).build();
    }

}///:~