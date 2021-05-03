package com.example.msscbrewery.controller;

import com.example.msscbrewery.exceptions.BreweryException;
import com.example.msscbrewery.model.CustomerDto;
import com.example.msscbrewery.service.CustomerService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getAllCustomers(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto, Errors errors) {
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return customerService.createCustomer(customerDto);
    }


    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable UUID customerId, @Valid @RequestBody CustomerDto customerDto, Errors errors) {
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return customerService.updateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteCustomerById(@PathVariable UUID customerId) {
        customerService.deleteCustomer(customerId);
    }
}
