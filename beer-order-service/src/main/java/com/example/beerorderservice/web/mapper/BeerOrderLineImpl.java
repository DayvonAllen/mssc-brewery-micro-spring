package com.example.beerorderservice.web.mapper;

import com.example.beerorderservice.domain.BeerOrderLine;
import com.example.beerorderservice.web.model.BeerOrderLineDto;
import org.springframework.stereotype.Component;

@Component
public class BeerOrderLineImpl implements BeerOrderLineMapper {

    private final DateMapper dateMapper;

    public BeerOrderLineImpl(DateMapper dateMapper) {
        this.dateMapper = dateMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        if(line == null) {
            return null;
        }
        return BeerOrderLineDto
                .builder()
                .beerId(line.getBeerId())
                .orderQuantity(line.getOrderQuantity())
                .createdDate(dateMapper.asOffsetDateTime(line.getCreatedDate()))
                .lastModifiedDate(dateMapper.asOffsetDateTime(line.getLastModifiedDate()))
                .build();
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        if(dto == null) {
            return null;
        }
        return BeerOrderLine
                .builder()
                .beerId(dto.getBeerId())
                .orderQuantity(dto.getOrderQuantity())
                .createdDate(dateMapper.asTimestamp(dto.getCreatedDate()))
                .lastModifiedDate(dateMapper.asTimestamp(dto.getLastModifiedDate()))
                .build();
    }
}
