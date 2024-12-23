package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.Roles;
import lombok.AllArgsConstructor;

import java.util.Date;

//Basic Frequently Used information
public record UserDTO(String name, String email, Date createdAt,
                      Roles role) {}
