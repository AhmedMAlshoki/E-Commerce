package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CustomerMapper {

    // Map Customer entity to CustomerDTO

    CustomerDTO CustomertoCustomerDTO(Customer customer);

    /*
    * CustomerProfileMapper <-> CustomerProfileDTO
    * */

    // Map CustomerDTO back to Customer entity
    Customer CustomerDTOtoCustomer(CustomerDTO customerDTO);
}
