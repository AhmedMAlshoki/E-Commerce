package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDTO(
        Long id,
        @JsonIgnore
        String addressLine1,
        @JsonIgnore
        String addressLine2,
        String city,
        String state,
        Country country,
        String zipCode
) {
}
