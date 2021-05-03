package com.example.msscbrewery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
    @Null(message = "ID can't be set by the client")
    private UUID id;

    @Size(min = 3, max = 100, message = "Name must be at least 3 characters and can't be more than 100 characters")
    @NotNull(message = "Name can't be null")
    private String name;
}
