package com.example.msscbrewery.service;

import com.example.msscbrewery.model.CustomerDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto getCustomerById(UUID id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(UUID id, CustomerDto beerDto);
    void deleteCustomer(UUID id);
}
