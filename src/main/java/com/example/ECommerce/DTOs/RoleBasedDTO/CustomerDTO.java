package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public record CustomerDTO(Long id,
                          String username,
                          String email,
                          String phoneNumber,
                          Double balance,
                          AddressDTO personalAddress)
        implements UserDTO {
}
