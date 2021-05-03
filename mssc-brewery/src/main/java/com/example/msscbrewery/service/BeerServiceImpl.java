package com.example.msscbrewery.service;

import com.example.msscbrewery.model.BeerDto;
import com.example.msscbrewery.model.BeerStyleEnum;
import com.example.msscbrewery.model.BeerStyleEnumValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.msscbrewery.model.BeerStyleEnumValue.*;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
    List<BeerDto> beers = new ArrayList<>();

    public BeerServiceImpl() {
        generateBeer();
    }

    @Override
    public List<BeerDto> getBeer() {
        return beers;
    }

    @Override
    public BeerDto getBeerById(UUID id) {
        return beers.get(0);
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        beerDto.setId(UUID.randomUUID());
        beerDto.setUpc(getRandomNumberUsingInts());
        beers.add(beerDto);
        return beers.get(beers.size() - 1);
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        boolean foundBeer = false;
        Integer updatedBeerIndex = null;
        BeerDto currentBeer;
        for(int i = 0; i < beers.size(); i++) {
            currentBeer = beers.get(i);
            if(currentBeer.getId().equals(id)) {
                foundBeer = true;
                currentBeer.setBeerName(beerDto.getBeerName());
                currentBeer.setBeerStyle(beerDto.getBeerStyle());
                updatedBeerIndex = i;
                break;
            }
        }

        if(!foundBeer) {
            log.info("Beer with ID {} was not found", id.toString());
            throw new RuntimeException("Beer with ID " + id + " was not found");
        }

        log.info("Updated beer with ID {}", id);
        return beers.get(updatedBeerIndex);
    }

    @Override
    public void deleteBeer(UUID id) {
        boolean foundBeer = false;
        BeerDto currentBeer;
        BeerDto foundBeerObj = null;
        for (BeerDto beer : beers) {
            currentBeer = beer;
            if (currentBeer.getId().equals(id)) {
                foundBeer = true;
                foundBeerObj = currentBeer;
                break;
            }
        }

        if(!foundBeer) {
            log.info("Beer with ID {} was not found", id.toString());
            throw new RuntimeException("Beer with ID " + id + " was not found");
        }

        beers.remove(foundBeerObj);
        log.info("Deleted beer with ID {}", id);
    }

    private void generateBeer() {
        BeerStyleEnum[] beerStyleEnumValues = {BeerStyleEnum.PALE_ALE, BeerStyleEnum.IPA, BeerStyleEnum.ALE};
        String[] names = {"Blue Moon", "Asahi", "Sapporo"};
        for(int i = 0; i < beerStyleEnumValues.length; i++) {
            beers.add(BeerDto
                    .builder()
                    .id(UUID.randomUUID())
                    .beerName(names[i])
                    .beerStyle(beerStyleEnumValues[i].toString())
                    .upc(getRandomNumberUsingInts())
                    .build());
        }

    }

    // generate random UPC
    private Integer getRandomNumberUsingInts() {
        Random random = new Random();
         return random.ints(10000000, 20000000)
                .findFirst()
                 .getAsInt();
    }

}



