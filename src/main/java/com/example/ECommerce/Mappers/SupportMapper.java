package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.SupportDTO;
import com.example.ECommerce.Entities.SubEntities.Support;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {AddressMapper.class})
public interface SupportMapper {
    SupportDTO supportToSupportDTO(Support support);
    Support supportDTOToSupport(SupportDTO supportDTO);
}
