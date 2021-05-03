package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @Test
    void testGetCustomerById() {
        CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void testSaveNewCustomer() {
        CustomerDto customerDto = client.saveNewCustomer(CustomerDto.builder()
                .name("Test")
                .build());

        assertNotNull(customerDto);

        System.out.println(customerDto);
    }

    @Test
    void testUpdateCustomer() {
        client.updateCustomer(UUID.fromString("d8268c34-1f1e-4f21-b14c-14aab83e446a"), CustomerDto.builder()
                .name("Corona")
                .build());

    }

    @Test
    void testDeleteBeer() {
        client.deleteCustomer(UUID.fromString("d8268c34-1f1e-4f21-b14c-14aab83e446a"));
    }
}
