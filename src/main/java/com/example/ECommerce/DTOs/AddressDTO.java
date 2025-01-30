package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.Country;

public record AddressDTO(
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        Country country,
        String zipCode
) {
}
