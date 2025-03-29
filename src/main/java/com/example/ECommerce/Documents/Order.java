package com.example.ECommerce.Documents;


import com.example.ECommerce.Enums.STATUS;
import com.mongodb.lang.NonNull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private Long userId;
    private Long productId;
    private Long shippingAddress;
    private Long destinationAddress;
    private Long paymentId;
    private STATUS status;
    private int quantity=1;
}
