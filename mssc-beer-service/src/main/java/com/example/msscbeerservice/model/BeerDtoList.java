package com.example.msscbeerservice.model;

import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BeerDtoList extends PageImpl<BeerDto> {

    private List<BeerDto> beer;

    public BeerDtoList(List<BeerDto> content) {
        super(content);
    }
}
