package com.example.ECommerce.DTOs;

import com.example.ECommerce.Enums.STATUS;

import java.util.Date;

public record OrderDTO(String id, Long userId, Long productId,
                       AddressDTO shippingAddress, AddressDTO destinationAddress,
                       PaymentDTO payment, int quantity, STATUS status) {
}
