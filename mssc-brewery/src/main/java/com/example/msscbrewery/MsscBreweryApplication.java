package com.example.msscbrewery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class MsscBreweryApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsscBreweryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(UUID.randomUUID());
    }
}
