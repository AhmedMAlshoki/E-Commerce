package com.example.ECommerce.DTOs;

import java.util.Date;

public record OrderDTO(String id, String userId, String productId,
                       AddressDTO shippingAddress, AddressDTO sourceAddress,
                       PaymentDTO payment, int quantity, Date orderDate) {
}
