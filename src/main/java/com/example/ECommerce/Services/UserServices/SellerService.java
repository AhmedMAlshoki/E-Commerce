package com.example.ECommerce.Services.UserServices;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Repositories.RoleBasedRepositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService  {

    private final SellerRepository sellerRepository;
    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    public User getSeller(Long id) {
        return sellerRepository.findById(id).orElseThrow();
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
