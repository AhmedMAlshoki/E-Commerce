package com.example.ECommerce.DTOs;

import java.util.Date;

public record ReviewDTO(String id, String userId,
                        String productId, int rating, String comment,
                        Date createdAt) {
}
