package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserProfileDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public UserService(UserRepository userRepository, @Lazy CustomerService customerService ,@Lazy SupportService supportService,
                       @Lazy AdminService adminService ,@Lazy SellerService sellerService) {
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

    public Roles getUserRole(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getRole();
    }

    public UserDTO getUser(Long id) throws Exception {
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


    public UserDTO updateUser(Long id, JsonNode jsonObject) {
        User user = userRepository.findById(id).orElseThrow();
        Roles role = user.getRole();
        try {
            return switch (role) {
                case CUSTOMER -> customerService.updateCustomer(id, jsonObject);
                case SUPPORT -> supportService.updateSupport(id, jsonObject);
                case ADMIN -> adminService.updateAdmin(id, jsonObject);
                case SELLER -> sellerService.updateSeller(id, jsonObject);
            };
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }

    public UserProfileDTO getUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Roles role = user.getRole();
        return switch (role) {
            case CUSTOMER -> customerService.getCustomerProfile(id);
            case SUPPORT -> supportService.getSupportProfile(id);
            case ADMIN -> adminService.getAdminProfile(id);
            case SELLER -> sellerService.getSellerProfile(id);
        };
    }
}
