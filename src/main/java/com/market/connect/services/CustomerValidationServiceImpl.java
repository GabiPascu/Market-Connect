package com.market.connect.services;

import com.market.connect.esxceptions.DuplicateCustomerException;
import com.market.connect.models.dtos.CustomerDto;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    private final CustomerRepository customerRepository;

    public CustomerValidationServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void validateUniqueCustomer(CustomerDto customerDto) {
        Customer foundCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (foundCustomer != null) {
            throw new DuplicateCustomerException("Email already exists.");
        }
    }
}
