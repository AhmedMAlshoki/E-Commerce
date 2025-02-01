package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.UserMapper;
import com.example.ECommerce.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final SupportService supportService;
    private final AdminService adminService;
    private final SellerService sellerService;
    @Autowired
    public UserService(UserRepository userRepository, CustomerService customerService , SupportService supportService,
                       AdminService adminService , SellerService sellerService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.supportService = supportService;
        this.adminService = adminService;
        this.sellerService = sellerService;
    }


    public UserDTO registerUser(UserRegisterationDTO userRegisterationDTO) throws Exception {

        if (userRepository.existsByEmail(userRegisterationDTO.email())) {
            throw new RuntimeException("Email already exists");
        } else if (userRepository.existsByUsername(userRegisterationDTO.username())) {
            throw new RuntimeException("Username already taken");
        }
        Roles role = userRegisterationDTO.isBusinessAccount() ? Roles.CUSTOMER : Roles.SUPPORT;


        return switch (role) {
            case CUSTOMER -> customerService.registerCustomer(userRegisterationDTO);
            case SUPPORT -> supportService.registerSupport(userRegisterationDTO);
            default -> null;
        };
    }

    public List<User> getRoleUsers(String role) {
        return userRepository.findByRole(Roles.valueOf(role));
    }


    public User getUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow();
        Roles role = user.getRole();
        return switch (role) {
            case CUSTOMER -> customerService.getCustomer(id);
            case SUPPORT -> supportService.getSupport(id);
            case ADMIN -> adminService.getAdmin(id);
            case SELLER -> sellerService.getSeller(id);
        };
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Roles role = user.getRole();
        switch (role) {
            case CUSTOMER -> customerService.deleteCustomer(id);
            case SUPPORT -> supportService.deleteSupport(id);
            case ADMIN -> adminService.deleteAdmin(id);
            case SELLER -> sellerService.deleteSeller(id);
        }
    }


}
