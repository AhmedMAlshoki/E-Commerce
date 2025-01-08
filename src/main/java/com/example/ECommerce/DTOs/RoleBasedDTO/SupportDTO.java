package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.UserDTO;
import com.example.ECommerce.Entities.Report;

import java.util.List;
import java.util.Set;

public record SupportDTO(UserDTO userInfo , Set<Report> reportsSolved) {
}
