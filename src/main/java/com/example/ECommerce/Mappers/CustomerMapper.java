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
    @Mapping(target = "loyaltyPoints", ignore = true)
    @Mapping(target = "purchasedProducts", ignore = true)
    @Mapping(target = "wishListedProducts", ignore = true)
    @Mapping(target = "orderIds", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "reviewIds", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    Customer CustomerDTOtoCustomer(CustomerDTO customerDTO);
}
