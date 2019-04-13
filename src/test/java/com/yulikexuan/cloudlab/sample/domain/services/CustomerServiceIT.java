//: com.yulikexuan.cloudlab.sample.domain.services.CustomerServiceIT.java


package com.yulikexuan.cloudlab.sample.domain.services;


import com.yulikexuan.cloudlab.sample.bootstrap.DefaultLoader;
import com.yulikexuan.cloudlab.sample.domain.model.Customer;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICategoryRepository;
import com.yulikexuan.cloudlab.sample.domain.repositories.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


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

    @DisplayName("Able to pathc existing custoemr - ")
    @Test
    void testPatchCustomer() {

        // Given
        Customer customer = Customer.builder()
                .id(3L)
                .firstname("Steve")
                .build();

        // When
        Customer patchedCustoemr = this.customerService.patchCustomer(customer);
        Customer actualPatchedCustomer = this.customerRepository
                .findById(patchedCustoemr.getId())
                .get();

        // Then
        assertThat(patchedCustoemr).isEqualTo(actualPatchedCustomer);

        assertAll("Assert the patched customer - ",
                () -> assertThat(patchedCustoemr.getId())
                        .as("The id of the patched customer should be %d",
                                customer.getId())
                        .isEqualTo(customer.getId()),
                () -> assertThat(patchedCustoemr.getFirstname())
                        .as("The firstname of the patched customer should be '%s'",
                                customer.getFirstname())
                        .isEqualTo(customer.getFirstname())
        );
    }

    @DisplayName("Able to delete a existing customer - ")
    @Test
    void testDeleteCustomer() {

        // Given
        Long id = 3L;

        // When
        this.customerService.deleteCustomer(id);
        Optional<Customer> deletedCustomerOpt = this.customerService.getCustomerById(id);

        // Then
        assertThat(deletedCustomerOpt.isPresent())
                .as("The customer with id %d has been deleted.", id)
                .isEqualTo(false);
    }

}///:~