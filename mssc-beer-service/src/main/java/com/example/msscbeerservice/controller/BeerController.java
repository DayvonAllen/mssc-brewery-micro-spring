package com.example.msscbeerservice.controller;

import com.example.msscbeerservice.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable UUID beerId) {
        return null;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@RequestBody BeerDto beerDto) {
        return null;
    }

    @PostMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        return null;
    }
}
