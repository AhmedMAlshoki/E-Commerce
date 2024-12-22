package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.Product;

import java.util.List;
import java.util.Set;

public record CustomerProfileDTO(CustomerDTO user, Set<Product> wishListedProducts,
                                 Set<Product> purchasedProducts ,Set<Review> reviews) {
}
