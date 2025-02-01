package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;
import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.Report;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public record SellerProfileDTO(Long id,String name, String email, Date createdAt, String phoneNumber,
                               Double amountSpent, Double amountSaved, Integer LoyaltyPoints,
                               Double balance, AddressDTO personalAddress,
                               Set<Product> wishListedProducts,
                               Set<Product> purchasedProducts , Set<Review> reviews ,
                               Set<Order> orders , List<Report> reports,
                               List<Product> ownedProducts,
                               String businessName, AddressDTO shippingAddress
                               , List<Offer> offers) implements UserProfileDTO{
}
