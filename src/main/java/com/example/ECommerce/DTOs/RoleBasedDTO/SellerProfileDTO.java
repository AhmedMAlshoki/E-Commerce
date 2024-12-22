package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;

import java.util.List;

public record SellerProfileDTO(SellerDTO user, List<Product> ownedProducts,
                               AddressDTO shippingAddress,List<Offer> offers) {
}
