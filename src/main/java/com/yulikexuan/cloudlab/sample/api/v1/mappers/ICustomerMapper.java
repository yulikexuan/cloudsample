//: com.yulikexuan.cloudlab.sample.api.v1.mappers.ICustomerMapper.java


package com.yulikexuan.cloudlab.sample.api.v1.mappers;


import com.yulikexuan.cloudlab.sample.api.v1.model.CustomerDTO;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    // CustomerDTO customerToCustomerDto(Customer customer);
    default CustomerDTO customerToCustomerDto(Customer customer) {

        if ( customer == null ) {
            return null;
        }

        return CustomerDTO.builder().firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .customerUrl("/api/v1/customers/" + customer.getId())
                .build();
    }

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

}///:~