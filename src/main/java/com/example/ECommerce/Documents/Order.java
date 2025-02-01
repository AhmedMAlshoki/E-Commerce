package com.example.ECommerce.Documents;

import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.Payment;
import com.example.ECommerce.Enums.STATUS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    //here I want @CreatedBy
    private Long userId;
    private Long productId;
    private Address shippingAddress;
    private Address sourceAddress;
    private Payment payment;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    int quantity;
}
