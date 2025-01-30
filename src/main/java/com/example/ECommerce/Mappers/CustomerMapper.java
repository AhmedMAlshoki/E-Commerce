package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CustomerMapper {

    // Map Customer entity to CustomerDTO

    @Mapping(source = "personalAddress", target = "personalAddress")
    CustomerDTO CustomertoCustomerDTO(Customer customer);


    // Map CustomerDTO back to Customer entity
    @Mapping(source = "personalAddress", target = "personalAddress")
    Customer CustomerDTOtoCustomer(CustomerDTO customerDTO);
}
