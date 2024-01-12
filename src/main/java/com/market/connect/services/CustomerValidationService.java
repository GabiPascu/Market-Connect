package com.market.connect.services;

import com.market.connect.models.dtos.CustomerDto;

public interface CustomerValidationService {

    void validateUniqueCustomer(CustomerDto customerDto);
}
