package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.Entities.Report;

import java.util.Set;

public record SupportDTO(UserDTO userInfo , Set<Report> reportsSolved)  implements UserDTO {
}
