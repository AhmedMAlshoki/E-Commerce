package com.example.ECommerce.Repositories.Redis;

import com.example.ECommerce.RedisHash.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    Cart findByUserId(Long userId);
}