package com.example.ECommerce.DTOs;

import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public record ProductProfileDTO(Long id, String name, String description, Double price, Integer quantity, Double rating ,
                                String category, SellerDTO seller, OfferDTO offer) {
}
