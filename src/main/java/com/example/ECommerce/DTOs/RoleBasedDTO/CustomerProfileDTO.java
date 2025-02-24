package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.DTOs.OrderDTO;
import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.Report;
import com.example.ECommerce.Enums.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public record CustomerProfileDTO(Long id,String name, String email, Date createdAt, String phoneNumber,
                                 Double amountSpent, Double amountSaved, Integer LoyaltyPoints,
                                 Roles role, Double balance, AddressDTO personalAddress,
                                 Set<ProductDTO> wishListedProducts,
                                 Set<ProductDTO> purchasedProducts
) implements UserProfileDTO {
}
