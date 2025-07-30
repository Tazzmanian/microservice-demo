package org.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .email(customerRequest.email())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .build();

        customer = customerRepository.saveAndFlush(customer);

        FraudCheckResponse resp = restTemplate.getForObject("http://localhost:8081/api/v1/frauds/{customerId}",
                FraudCheckResponse.class, customer.getId());

        if (resp.isFraudulent()) {
            throw new RuntimeException("fraudulent");
        }

        log.info("customer registered: {}", customer);
    }
}
