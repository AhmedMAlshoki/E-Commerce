package com.example.ECommerce.DTOs;

import com.example.ECommerce.Entities.Product;

import java.util.Map;

public record CartDTO(String id , Long userId, Map<Long, Long> items) {
}
