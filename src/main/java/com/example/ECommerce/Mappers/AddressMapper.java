package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO addressToAddressDTO(Address address);

    Address addressDTOToAddress(AddressDTO addressDTO);
}
