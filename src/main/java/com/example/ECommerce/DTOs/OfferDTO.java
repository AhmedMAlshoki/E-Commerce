package com.example.ECommerce.DTOs;

import java.util.Date;

public record OfferDTO(Long id, Double discount, Long productId,
                       Long sellerId, Date startDate, Date endDate) {
}
