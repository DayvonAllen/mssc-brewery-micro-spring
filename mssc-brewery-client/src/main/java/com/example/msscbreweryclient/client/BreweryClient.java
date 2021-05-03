package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.BeerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
// will add this prefix for everything in this class when determining how to inject values from "application.properties"
// if ignoreUnknownFields is set to try and "apihost" is not set then and exception will occur.
//@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {
    @Value("${sfg.brewery.apihost}")
    private String apihost;

    @Value("${sfg.brewery.beerpath}")
    public String beerPath;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID id) {
        // arg 1 = rest path, arg 2 = class of the object we are parsing
        return restTemplate.getForObject(apihost + beerPath + "/" + id, BeerDto.class);
    }

    public BeerDto saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForObject(apihost + beerPath, beerDto, BeerDto.class);
    }

    public void updateBeer(UUID id, BeerDto beerDto) {
        restTemplate.put(apihost + beerPath + "/" + id, beerDto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apihost + beerPath + "/" + id);
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public void setBeerPath(String beerPath) {
        this.beerPath = beerPath;
    }
}
