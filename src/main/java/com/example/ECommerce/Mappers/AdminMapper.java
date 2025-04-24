package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin AdminDTOToAdmin(AdminDTO adminDTO);

    AdminDTO AdminToAdminDTO(Admin admin);
}
