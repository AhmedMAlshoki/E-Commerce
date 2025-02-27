package com.example.ECommerce.DTOs;

import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public record ProductDTO(Long id, String name, String description, String category,
                         Double price, Integer quantity, Double rating ,
                            Long seller) {
}
