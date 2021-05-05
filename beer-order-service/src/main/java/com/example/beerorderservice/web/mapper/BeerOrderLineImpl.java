package com.example.beerorderservice.web.mapper;

import com.example.beerorderservice.domain.BeerOrderLine;
import com.example.beerorderservice.web.model.BeerOrderLineDto;
import org.springframework.stereotype.Component;

@Component
public class BeerOrderLineImpl implements BeerOrderLineMapper {
    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        return null;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        return null;
    }
}
