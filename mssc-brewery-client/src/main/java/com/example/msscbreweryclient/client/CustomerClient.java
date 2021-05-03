package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
// will add this prefix for everything in this class when determining how to inject values from "application.properties"
// if ignoreUnknownFields is set to try and "apihost" is not set then and exception will occur.
//@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class CustomerClient {
    @Value("${sfg.brewery.apihost}")
    private String apihost;

    @Value("${sfg.brewery.customerpath}")
    public String customerPath;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID id) {
        // arg 1 = rest path, arg 2 = class of the object we are parsing
        return restTemplate.getForObject(apihost + customerPath + "/" + id, CustomerDto.class);
    }

    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForObject(apihost + customerPath, customerDto, CustomerDto.class);
    }

    public void updateCustomer(UUID id, CustomerDto customerDto) {
        restTemplate.put(apihost + customerPath + "/" + id, customerDto);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(apihost + customerPath + "/" + id);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
