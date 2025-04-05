package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.SupportDTO;
import com.example.ECommerce.Entities.SubEntities.Support;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupportMapper {


    SupportDTO supportToSupportDTO(Support support);
    @Mapping(target = "id", ignore = false)
    @Mapping(target = "reportsSolved",ignore = true)
    Support supportDTOToSupport(SupportDTO supportDTO);
}
