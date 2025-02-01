package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;

public record CustomerDTO(Long id,
                          String name,
                          String email,
                          String phoneNumber,
                          Double balance,
                          AddressDTO personalAddress)
        implements UserDTO {
}
