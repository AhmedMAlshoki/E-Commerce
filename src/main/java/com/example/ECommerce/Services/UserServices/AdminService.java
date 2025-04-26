package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserProfileDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import com.example.ECommerce.Entities.SubEntities.Support;
import com.example.ECommerce.Enums.Roles;
import com.example.ECommerce.Mappers.AdminMapper;
import com.example.ECommerce.Mappers.ProfilesMapper;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.AdminRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdminService{
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final SupportService supportService;
    private final ProfilesMapper profilesMapper;
    @Autowired
    public AdminService(AdminRepository adminRepository , AdminMapper adminMapper,
                        SupportService supportService,@Lazy ProfilesMapper profilesMapper) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
        this.supportService = supportService;
        this.profilesMapper = profilesMapper;
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

    public AdminDTO updateAdmin(Long id, JsonNode jsonNode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper() .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
        Admin existingAdmin = adminRepository.findById(id).orElseThrow();
        existingAdmin = objectMapper.readerForUpdating(existingAdmin).readValue(jsonNode);
        adminRepository.save(existingAdmin);
        //To make sure the updated Address will be returned within the entity
        Admin theUpdatedAdmin = adminRepository.findById(id).orElseThrow();
        return adminMapper.AdminToAdminDTO(theUpdatedAdmin);
    }

    public UserProfileDTO getAdminProfile(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow();
        return profilesMapper.adminToAdminProfileDTO(admin);
    }


    /*
    *Admin Profile
    *
    *
    * */
}
