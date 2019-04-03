//: com.yulikexuan.cloudlab.sample.domain.services.ICustomerService.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Customer;

import java.util.List;
import java.util.Optional;


public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);

}///:~