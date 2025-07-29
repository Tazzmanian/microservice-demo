package org.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .email(customerRequest.email())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .build();
        customer = customerRepository.save(customer);
        log.info("customer registered: {}", customer);
    }
}
