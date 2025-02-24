package com.example.ECommerce.DTOs.RoleBasedDTO;

public record AdminProfileDTO(
        Long id,String username, String email, String phoneNumber
) implements UserProfileDTO {
}
