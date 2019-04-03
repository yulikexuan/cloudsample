//: com.yulikexuan.cloudlab.sample.domain.repositories.ICustomerRepository.java


package com.yulikexuan.cloudlab.sample.domain.repositories;


import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}///:~