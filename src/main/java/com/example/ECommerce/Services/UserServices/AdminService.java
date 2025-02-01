package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Repositories.RoleBasedRepositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public User getAdmin(Long id) {
        return adminRepository.findById(id).orElseThrow();
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
