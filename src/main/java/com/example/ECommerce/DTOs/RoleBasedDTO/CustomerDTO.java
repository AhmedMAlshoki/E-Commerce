package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.DTOs.UserDTO;
import com.example.ECommerce.Enums.Roles;

import java.util.Date;

public record CustomerDTO(String name, String email, Date createdAt, String phoneNumber,
                          Double balance, AddressDTO personalAddress) {
}
