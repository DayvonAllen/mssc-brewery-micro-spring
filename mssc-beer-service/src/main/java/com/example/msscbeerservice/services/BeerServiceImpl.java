package com.example.msscbeerservice.services;

import com.example.msscbeerservice.exceptions.BreweryException;
import com.example.msscbeerservice.mapper.BeerMapper;
import com.example.msscbeerservice.model.BeerDto;
import com.example.msscbeerservice.repo.BeerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

// this annotation can be used by lombok to automatically set all of the private properties in a constructor
// spring will then automatically autowire them into the class
//@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepo beerRepo;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepo beerRepo, BeerMapper beerMapper) {
        this.beerRepo = beerRepo;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto getBeerById(UUID id) {
        log.info("Looking for beer with ID {}...", id);
        return beerMapper
                .beerToBeerDtoMapper(beerRepo
                        .findById(id)
                        .orElseThrow(() -> new BreweryException("Couldn't find beer with the ID " + id)));
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        log.info("Creating a beer...");
        return beerMapper
                .beerToBeerDtoMapper(beerRepo
                        .save(beerMapper
                                .beerDtoToBeerMapper(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID id, BeerDto beerDto) {
        // check if beer that is being updated exists before trying to update it.
        log.info("Looking for beer with ID {}...", id);
        beerMapper
                .beerToBeerDtoMapper(beerRepo
                        .findById(id)
                        .orElseThrow(() -> new BreweryException("Couldn't find beer with the ID " + id)));

        log.info("found beer with ID {}...", id);

        beerDto.setId(id);

        log.info("updating beer with ID {}...", id);
        return beerMapper
                .beerToBeerDtoMapper(beerRepo
                        .save(beerMapper
                                .beerDtoToBeerMapper(beerDto)));
    }
}
