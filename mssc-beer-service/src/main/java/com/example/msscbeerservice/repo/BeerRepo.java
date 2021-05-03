package com.example.msscbeerservice.repo;

import com.example.msscbeerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {
}
