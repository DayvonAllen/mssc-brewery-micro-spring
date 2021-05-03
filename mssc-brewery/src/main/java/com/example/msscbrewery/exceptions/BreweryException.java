package com.example.msscbrewery.exceptions;

public class BreweryException extends RuntimeException{
    public BreweryException() {
    }

    public BreweryException(String message) {
        super(message);
    }
}
