package com.example.msscbrewery.service;

import com.example.msscbrewery.model.BeerDto;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    List<BeerDto> getBeer();
    BeerDto getBeerById(UUID id);
    BeerDto createBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID id, BeerDto beerDto);
    void deleteBeer(UUID id);

}
