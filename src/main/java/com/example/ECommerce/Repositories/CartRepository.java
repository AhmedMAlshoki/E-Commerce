package com.example.ECommerce.Repositories;

import com.example.ECommerce.RedisHash.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(Long userId);
}