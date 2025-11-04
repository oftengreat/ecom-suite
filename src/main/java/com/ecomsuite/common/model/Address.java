package com.ecomsuite.common.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Optional: creates a default constructor


@Getter 
@Setter
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public Address(String street, String city, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters/Setters omitted
}

