package com.example.beerorderservice.web.mapper;

import com.example.beerorderservice.domain.BeerOrderLine;
import com.example.beerorderservice.web.model.BeerOrderLineDto;

public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
