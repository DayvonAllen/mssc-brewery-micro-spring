package com.example.beerinventoryservice.web.mapper;

import com.example.beerinventoryservice.domain.BeerInventory;
import com.example.beerinventoryservice.web.models.BeerInventoryDto;
import org.springframework.stereotype.Component;

@Component
public class BeerInventoryMapperImpl implements BeerInventoryMapper{

    private final DateMapper dateMapper;

    public BeerInventoryMapperImpl(DateMapper dateMapper) {
        this.dateMapper = dateMapper;
    }

    @Override
    public BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO) {
        if(beerInventoryDTO == null) {
            return null;
        }
        return BeerInventory
                .builder()
                .id(beerInventoryDTO.getBeerId())
                .beerId(beerInventoryDTO.getId())
                .upc(beerInventoryDTO.getUtc())
                .createdDate(dateMapper.asTimestamp(beerInventoryDTO.getCreatedDate()))
                .lastModifiedDate(dateMapper.asTimestamp(beerInventoryDTO.getLastModifiedDate()))
                .quantityOnHand(beerInventoryDTO.getQuantityOnHand())
                .build();
    }

    @Override
    public BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory) {
        if(beerInventory == null) {
            return null;
        }
        return BeerInventoryDto
                .builder()
                .id(beerInventory.getId())
                .beerId(beerInventory.getId())
                .utc(beerInventory.getUpc())
                .createdDate(dateMapper.asOffsetDateTime(beerInventory.getCreatedDate()))
                .lastModifiedDate(dateMapper.asOffsetDateTime(beerInventory.getLastModifiedDate()))
                .quantityOnHand(beerInventory.getQuantityOnHand())
                .build();
    }
}
