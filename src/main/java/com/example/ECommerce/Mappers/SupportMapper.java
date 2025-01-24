package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.SupportDTO;
import com.example.ECommerce.Entities.SubEntities.Support;

public interface SupportMapper {
    SupportDTO supportToSupportDTO(Support support);
}
