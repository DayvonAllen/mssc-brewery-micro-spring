package com.example.msscbrewery.service;

import com.example.msscbrewery.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    List<CustomerDto> customers = new ArrayList<>();

    public CustomerServiceImpl() {
        generateJohnDoe();
    }
    @Override
    public List<CustomerDto> getCustomers() {
        return customers;
    }

    @Override
    public CustomerDto getCustomerById(UUID id) {
        return customers.get(0);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        customerDto.setId(UUID.randomUUID());
        customers.add(customerDto);
        return customerDto;
    }

    @Override
    public CustomerDto updateCustomer(UUID id, CustomerDto customerDto) {
        boolean foundCustomer = false;
        Integer updatedCustomerIndex = null;
        CustomerDto currentCustomer;
        for(int i = 0; i < customers.size(); i++) {
            currentCustomer = customers.get(i);
            if(currentCustomer.getId().equals(id)) {
                foundCustomer = true;
                currentCustomer.setName(customerDto.getName());
                updatedCustomerIndex = i;
                break;
            }
        }

        if(!foundCustomer) {
            log.info("Customer with ID {} was not found", id.toString());
            throw new RuntimeException("Customer with ID " + id + " was not found");
        }

        log.info("Updated Customer with ID {}", id);
        return customers.get(updatedCustomerIndex);
    }

    @Override
    public void deleteCustomer(UUID id) {
        boolean foundCustomer = false;
        CustomerDto currentCurrent;
        CustomerDto foundCustomerObj = null;
        for (CustomerDto customer : customers) {
            currentCurrent = customer;
            if (currentCurrent.getId().equals(id)) {
                foundCustomer = true;
                foundCustomerObj = currentCurrent;
                break;
            }
        }

        if(!foundCustomer) {
            log.info("Customer with ID {} was not found", id.toString());
            throw new RuntimeException("Beer with ID " + id + " was not found");
        }

        customers.remove(foundCustomerObj);
        log.info("Deleted Customer with ID {}", id);
    }

    private void generateJohnDoe() {
        for(int i = 1; i <= 3; i++) {
            customers.add(CustomerDto
                    .builder()
                    .id(UUID.randomUUID())
                    .name("John Doe number " + i)
                    .build());
        }
    }
}
