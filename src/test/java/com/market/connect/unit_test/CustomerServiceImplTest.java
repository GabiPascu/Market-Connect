package com.market.connect.unit_test;

import com.market.connect.models.dtos.CustomerDto;
import com.market.connect.models.entities.Customer;
import com.market.connect.repositories.CustomerRepository;
import com.market.connect.services.CustomerServiceImpl;
import com.market.connect.services.CustomerValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomerTestShouldPass() {
        //GIVEN
        CustomerDto requestCustomerDto = CustomerDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();
        CustomerDto responseCustomerDto = CustomerDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();
        Customer customer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("John@gmail.com")
                .build();
        Customer savedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("John@gmail.com")
                .build();
        //WHEN
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(modelMapper.map(requestCustomerDto, Customer.class)).thenReturn(customer);
        when(modelMapper.map(savedCustomer, CustomerDto.class)).thenReturn(responseCustomerDto);

        CustomerDto customerDto = customerService.createCustomer(requestCustomerDto);

        //THEN
        verify(customerRepository, times(1)).save(customer);
        Assertions.assertNotNull(requestCustomerDto.getEmail(), customerDto.getEmail());

    }
}
