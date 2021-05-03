package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void testGetBeerById() {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void testSaveNewBeer() {
        BeerDto beerDto = client.saveNewBeer(BeerDto.builder()
                .beerName("Test")
                .beerStyle("ALE")
                .build());

        assertNotNull(beerDto);

        System.out.println(beerDto);
    }

    @Test
    void testUpdateBeer() {
        client.updateBeer(UUID.fromString("64728d71-a6f8-4ced-9052-e6fd12ef7a62"), BeerDto.builder()
                .beerName("Corona")
                .beerStyle("ALE")
                .build());

    }

    @Test
    void testDeleteBeer() {
        client.deleteBeer(UUID.fromString("64728d71-a6f8-4ced-9052-e6fd12ef7a62"));
    }
}
