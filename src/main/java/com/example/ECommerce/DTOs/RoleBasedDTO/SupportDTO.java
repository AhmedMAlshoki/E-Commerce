package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.Entities.Report;

import java.util.Set;

public record SupportDTO(Long id,String username, String email, String phoneNumber)  implements UserDTO {
}
