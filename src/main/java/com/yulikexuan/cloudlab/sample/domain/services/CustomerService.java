//: com.yulikexuan.cloudlab.sample.domain.services.CustomerService.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();

    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return this.customerRepository.findById(id);
    }

}///:~