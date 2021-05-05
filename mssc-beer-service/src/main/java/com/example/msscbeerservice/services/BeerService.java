package com.example.msscbeerservice.services;

import com.example.msscbeerservice.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID id);
    BeerDto createBeer(BeerDto beerDto);
    BeerDto updateBeerById(UUID id, BeerDto beerDto);

}
