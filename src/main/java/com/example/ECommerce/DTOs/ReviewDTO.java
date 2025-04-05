package com.example.ECommerce.DTOs;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;

import java.util.Date;

public record ReviewDTO(String id, CustomerDTO user,
                        ProductDTO product, int rating, String comment) {
}
