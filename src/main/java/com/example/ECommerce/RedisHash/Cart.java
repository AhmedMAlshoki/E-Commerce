package com.example.ECommerce.RedisHash;

import com.example.ECommerce.Entities.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RedisHash("cart")
@Data
public class Cart implements Serializable {
    @Id
    private String id;
    private Long userId; // Unique identifier (e.g., user ID or session ID)
    private Map<Long, Long> items = new HashMap<>(); // Product ID → CartItem

    @TimeToLive// Automatically expire the cart after TTL (e.g., 7 days)
    private Long expiration;
}
