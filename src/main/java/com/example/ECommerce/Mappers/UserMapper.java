package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.UserDTO;
import com.example.ECommerce.DTOs.UserLoginDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    User userRegisterationToUser(UserRegisterationDTO userDTO);
    User userLoginToUser(UserLoginDTO userDTO);
}
