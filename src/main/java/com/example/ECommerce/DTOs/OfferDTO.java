package com.example.ECommerce.DTOs;

import java.time.LocalDate;
import java.util.Date;

public record OfferDTO(Long id, Double discount, Long productId,
                       Long sellerId, LocalDate startDate, LocalDate endDate) {
}
