package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.ReportDTO;

import java.util.List;

public record SupportProfileDTO(
        Long id,String username, String email, String phoneNumber, List<ReportDTO> solverReports
) implements UserProfileDTO {
}
