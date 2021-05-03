package com.example.msscbrewery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BeerDto {

    @Null(message = "ID can't be set by client")
    private UUID id;

    @NotBlank(message = "Beer style can't be blank")
    private String beerName;

    @NotBlank(message = "Beer style can't be blank")
    private String beerStyle;

    private Integer upc;

}
