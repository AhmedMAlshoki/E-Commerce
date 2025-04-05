package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.CartDTO;
import com.example.ECommerce.RedisHash.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface CartMapper {
    CartDTO cartToCartDTO(Cart cart);
}
