//: com.yulikexuan.cloudlab.sample.api.v1.mappers.CustomerMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}///:~