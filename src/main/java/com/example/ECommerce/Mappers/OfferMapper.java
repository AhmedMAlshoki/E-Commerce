package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.OfferDTO;
import com.example.ECommerce.Entities.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ProductMapper.class,SellerMapper.class})
public interface OfferMapper {

    @Mapping(target = "seller",ignore = true)
    @Mapping(target = "product",ignore = true)
    Offer offerDTOToOffer(OfferDTO offerDTO);

    @Mapping(target = "productId",source = "product.id")
    @Mapping(target = "sellerId", source = "seller.id")
    OfferDTO offerToOfferDTO(Offer offer);
}
