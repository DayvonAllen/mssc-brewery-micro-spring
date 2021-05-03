package com.example.msscbrewery.controller;

import com.example.msscbrewery.exceptions.BreweryException;
import com.example.msscbrewery.model.BeerDto;
import com.example.msscbrewery.service.BeerService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getBeers() {
        return beerService.getBeer();
    }

    @GetMapping(value = "/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeer(@PathVariable UUID beerId) {
        return beerService.getBeerById(beerId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    // errors needed for getting validation errors
    public BeerDto createBeer(@Valid @RequestBody BeerDto beerDto, Errors errors) {
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
    // errors needed for getting validation errors
    public BeerDto updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto, Errors errors) {
        if(errors.hasErrors()) {
            throw new BreweryException(errors
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }

        return beerService.updateBeer(beerId, beerDto);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteBeerById(@PathVariable UUID beerId) {
        beerService.deleteBeer(beerId);
    }
}
