package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;

public interface AdminMapper {
    AdminDTO AdminToAdminDTO(Admin admin);
}
