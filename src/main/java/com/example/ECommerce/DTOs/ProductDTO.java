package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.Categories;

import java.util.List;

public record ProductDTO(Long id, String name, String description, Categories category,
                         Double price, Integer quantity, Double rating , List<ReviewDTO> reviews) {
}
