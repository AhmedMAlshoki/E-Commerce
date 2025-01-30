package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.Roles;
import lombok.Setter;


public record UserRegisterationDTO(String email,String username,String password, boolean isBusinessAccount) {
}
