package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import org.mapstruct.Mapping;

public interface AdminMapper {

    Admin AdminDTOToAdmin(AdminDTO adminDTO);
    AdminDTO AdminToAdminDTO(Admin admin);
}
