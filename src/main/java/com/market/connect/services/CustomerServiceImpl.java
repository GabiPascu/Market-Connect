package com.market.connect.services;

import com.market.connect.models.dtos.CustomerDto;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerValidationService customerValidationService,
                               ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerValidationService.validateUniqueCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(modelMapper.map(customerDto, Customer.class));
        log.info("Customer with id {}, saved in database.", savedCustomer.getId());

        return modelMapper.map(savedCustomer, CustomerDto.class);
    }
}
