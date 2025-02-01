package com.example.ECommerce.Mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class,SellerMapper.class})
public interface ProductMapper {
}
