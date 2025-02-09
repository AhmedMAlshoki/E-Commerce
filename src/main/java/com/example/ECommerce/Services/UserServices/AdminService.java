package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Mappers.AdminMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    @Autowired
    public AdminService(AdminRepository adminRepository , AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
    }
    public UserDTO getAdmin(Long id) {
        return adminMapper.AdminToAdminDTO(adminRepository.findById(id).orElseThrow());
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
