package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;

import java.util.Date;
import java.util.List;

public record SellerProfileDTO(String name, String email, Date createdAt,String phoneNumber,
                               Double balance, AddressDTO personalAddress,
                               List<Product> ownedProducts,
                               String businessName, AddressDTO shippingAddress
                               ,List<Offer> offers) {
}
