package com.example.ECommerce.Documents;

import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.Payment;
import com.example.ECommerce.Entities.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private Long userId;
    private Long productId;
    private Address shippingAddress;
    private Payment payment;
    private LocalDateTime orderDate;
}
