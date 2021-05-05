package com.example.beerorderservice.web.mapper;

import com.example.beerorderservice.domain.BeerOrder;
import com.example.beerorderservice.web.model.BeerOrderDto;

public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);
    BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
