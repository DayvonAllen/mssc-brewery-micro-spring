package com.example.msscbrewery.model;

public enum BeerStyleEnumValue {
    ALE("Ale"), PALE_ALE("Pale Ale"), IPA("IPA");

    public final String label;

    BeerStyleEnumValue(String label) {
        this.label = label;
    }
}
