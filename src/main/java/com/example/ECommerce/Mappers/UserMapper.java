package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.UserLoginDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.SubEntities.Support;
import com.example.ECommerce.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
 //   UserDTO userToUserDTO(User user);
 //   User userDTOToUser(UserDTO userDTO);
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User userRegisterationDTOToUser(UserRegisterationDTO userDTO);

    @Mapping(target = "role",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "reportsSolved", ignore = true)
    Support userRegisterationDTOToSupport(UserRegisterationDTO userDTO);
    @Mapping(target = "role",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "personalAddress", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "amountSpent", ignore = true)
    @Mapping(target = "amountSaved", ignore = true)
    @Mapping(target = "LoyaltyPoints", ignore = true)
    @Mapping(target = "purchasedProducts", ignore = true)
    @Mapping(target = "wishListedProducts", ignore = true)
    @Mapping(target = "orderIds", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "reviewIds", ignore = true)
    @Mapping(target = "reports", ignore = true)
    Customer userRegisterationDTOToCustomer(UserRegisterationDTO userDTO); // for support register
    User userLoginDTOToUser(UserLoginDTO userDTO);
}
