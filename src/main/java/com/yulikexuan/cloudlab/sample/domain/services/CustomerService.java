//: com.yulikexuan.cloudlab.sample.domain.services.CustomerService.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICustomerRepository;
import com.yulikexuan.cloudlab.sample.domain.services.exceptions.NotFoundException;
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

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = Optional.ofNullable(customer)
                .map(this.customerRepository::save)
                .orElseThrow(RuntimeException::new);
        return savedCustomer;
    }

    @Override
    public Customer patchCustomer(Customer customer) {

        return Optional.ofNullable(customer)
                .map(c -> this.customerRepository.findById(c.getId()))
                .map(copt -> copt.orElseThrow(NotFoundException::new))
                .map(c -> {
                    if (customer.getFirstname() != null) {
                        c.setFirstname(customer.getFirstname());
                    }
                    if (customer.getLastname() != null) {
                        c.setLastname(customer.getLastname());
                    }
                    return this.customerRepository.save(c);
                }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }

}///:~