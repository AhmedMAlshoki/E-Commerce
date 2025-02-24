package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.SellerDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerProfileDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.Entities.SubEntities.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface SellerMapper {


    @Mapping(source = "personalAddress", target = "personalAddress")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    SellerDTO sellerToSellerDTO(Seller seller);

    @Mapping(source = "personalAddress", target = "personalAddress")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(target = "loyaltyPoints", ignore = true)
    @Mapping(target = "purchasedProducts", ignore = true)
    @Mapping(target = "wishListedProducts", ignore = true)
    @Mapping(target = "orderIds", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "reviewIds", ignore = true)
    @Mapping(target = "reports", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "businessName", ignore = true)
    @Mapping(target = "taxId", ignore = true)
    @Mapping(target = "ownedProducts", ignore = true)
    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "role", ignore = true)
    Seller sellerDTOToSeller(SellerDTO sellerDTO);

}
