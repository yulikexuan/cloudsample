//: com.yulikexuan.cloudlab.sample.domain.services.CustomerServiceIT.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.bootstrap.DefaultLoader;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.Supplier;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class CustomerServiceIT {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    private CustomerService customerService;

    private final Supplier<Integer> customersTotalSupplier =
            () -> customerRepository.findAll().size();

    @BeforeEach
    void setUp() throws Exception {

        // Before loading data:
        System.out.printf(">>>>>>> Before loading data, there are %d customers.%n",
                this.customersTotalSupplier.get());

        // Set up data for testing:
        DefaultLoader defaultLoader = new DefaultLoader(this.categoryRepository,
                this.customerRepository);
        defaultLoader.run();
        this.customerService = new CustomerService(this.customerRepository);

        System.out.printf(">>>>>>> After loading data, there are %d customers.%n",
                this.customersTotalSupplier.get());
    }

    @Test
    void name() {

    }

}///:~