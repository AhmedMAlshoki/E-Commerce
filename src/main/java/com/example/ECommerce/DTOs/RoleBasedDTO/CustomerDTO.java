package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.DTOs.UserDTO;

public record CustomerDTO(UserDTO user, Double balance, AddressDTO personalAddress) {
}
