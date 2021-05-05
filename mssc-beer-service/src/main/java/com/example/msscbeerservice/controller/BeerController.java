package com.example.msscbeerservice.controller;

import com.example.msscbeerservice.exceptions.BreweryException;
import com.example.msscbeerservice.model.BeerDto;
import com.example.msscbeerservice.services.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beer")
@Slf4j
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable UUID beerId) {
        log.info("In the controller method 'getBeerById'");
        return beerService.getBeerById(beerId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@Valid @RequestBody BeerDto beerDto, Errors errors) {
        log.info("In the controller method 'createBeer'");
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return beerService.createBeer(beerDto);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto, Errors errors) {
        log.info("In the controller method 'updateBeer'");
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        return beerService.updateBeerById(beerId, beerDto);
    }
}
