package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "id", ignore = false)
    AddressDTO addressToAddressDTO(Address address);

    Address addressDTOToAddress(AddressDTO addressDTO);
}
