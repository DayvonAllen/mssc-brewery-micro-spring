package com.example.msscbeerservice.bootstrap;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.model.BeerStyleEnum;
import com.example.msscbeerservice.repo.BeerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepo beerRepo;

    public List<Beer> beers = new ArrayList<>();

    public BeerLoader(BeerRepo beerRepo) {
        this.beerRepo = beerRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if(beerRepo.count() == 0) {
            generateBeer();
            beerRepo.saveAll(beers);
        }
    }

    private void generateBeer() {
        BeerStyleEnum[] beerStyleEnumValues = {BeerStyleEnum.PALE_ALE, BeerStyleEnum.IPA, BeerStyleEnum.ALE};
        String[] names = {"Blue Moon", "Asahi", "Sapporo"};
        String[] price = {"20.12", "23.23", "43.12"};
        for(int i = 0; i < beerStyleEnumValues.length; i++) {
            beers.add(Beer
                    .builder()
                    .id(UUID.randomUUID())
                    .beerName(names[i])
                    .quantityToBrew(200)
                    .price(new BigDecimal(price[i]))
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
