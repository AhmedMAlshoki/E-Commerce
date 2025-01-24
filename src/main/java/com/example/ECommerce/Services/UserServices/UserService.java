package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomerService customerService;
    private final SupportService supportService;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, CustomerService customerService , SupportService supportService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.supportService = supportService;
    }


    public UserDTO registerUser(UserRegisterationDTO userRegisterationDTO) throws Exception {

        /*
        * User user = userMapper.userRegisterationDTOToUser(userRegisterationDTO);
            userRepository.save(user);
            if (userRegisterationDTO.role() == Roles.CUSTOMER) {
                return customerService.registerCustomer(userRegisterationDTO);
                * */
        if (userRepository.existsByEmail(userRegisterationDTO.email())) {
            throw new RuntimeException("Email already exists");
        } else if (userRepository.existsByUsername(userRegisterationDTO.username())) {
            throw new RuntimeException("Username already taken");
        }
        Roles role = userRegisterationDTO.isBusinessAccount() ? Roles.CUSTOMER : Roles.SUPPORT;

        User user = userMapper.userRegisterationDTOToUser(userRegisterationDTO);
        UserDTO userDTO = null;
        userRepository.save(user);
        switch (role) {
            case CUSTOMER:
                userDTO = customerService.registerCustomer(userRegisterationDTO);
            case SUPPORT:
                userDTO =supportService.registerSupport(userRegisterationDTO);
        }
        return userDTO;
    }
}
