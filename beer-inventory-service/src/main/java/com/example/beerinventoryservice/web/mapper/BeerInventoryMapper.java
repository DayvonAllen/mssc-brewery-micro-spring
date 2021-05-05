package com.example.beerinventoryservice.web.mapper;

import com.example.beerinventoryservice.domain.BeerInventory;
import com.example.beerinventoryservice.web.models.BeerInventoryDto;

public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
