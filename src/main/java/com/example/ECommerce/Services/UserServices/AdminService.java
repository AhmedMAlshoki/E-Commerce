package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import com.example.ECommerce.Entities.SubEntities.Support;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.AdminMapper;
import com.example.ECommerce.Repositories.RoleBasedRepositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SupportService supportService;
    @Autowired
    public AdminService(AdminRepository adminRepository , AdminMapper adminMapper,
                        SupportService supportService) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.supportService = supportService;
    }
    public AdminDTO getAdmin(Long id) {
            return adminMapper.AdminToAdminDTO(adminRepository.findById(id).orElseThrow());
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public AdminDTO PromoteToAdmin(Long id) {
        try {
            Support support = supportService.getSupportByIdToPromote(id);
            Admin admin = new Admin();
            admin.setId(support.getId());
            admin.setUsername(support.getUsername());
            admin.setEmail(support.getEmail());
            admin.setPhoneNumber(support.getPhoneNumber());
            admin.setPassword(support.getPassword());
            admin.setRole(Roles.ADMIN);
            admin.setCreatedAt(support.getCreatedAt());
            supportService.deleteSupport(id);
            adminRepository.save(admin);
            return adminMapper.AdminToAdminDTO(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*
    *Admin Profile
    *
    *
    * */
}
