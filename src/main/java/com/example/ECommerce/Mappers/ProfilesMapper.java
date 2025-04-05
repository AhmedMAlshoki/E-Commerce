package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminProfileDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerProfileDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SellerProfileDTO;
import com.example.ECommerce.DTOs.RoleBasedDTO.SupportProfileDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Entities.SubEntities.Support;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class,ProductMapper.class, OfferMapper.class})
public interface ProfilesMapper {

    @Mapping(target="personalAddress",source="personalAddress")
    @Mapping(target="wishListedProducts",source = "wishListedProducts")
    @Mapping(target="purchasedProducts",source = "purchasedProducts")
    @Mapping(target = "id", ignore = false)
    CustomerProfileDTO customerToCustomerProfileDTO(Customer customer);

    @Mapping(target="shippingAddress",source="shippingAddress")
    @Mapping(target="personalAddress",source="personalAddress")
    @Mapping(target="wishListedProducts",source = "wishListedProducts")
    @Mapping(target="purchasedProducts",source = "purchasedProducts")
    @Mapping(target="ownedProducts",source = "ownedProducts")
    @Mapping(target="offers",source = "offers")
    @Mapping(target = "id", ignore = false)
    SellerProfileDTO sellerToSellerProfileDTO(Seller seller);

    @Mapping(target = "id", ignore = false)
    AdminProfileDTO adminToAdminProfileDTO(Admin admin);

    @Mapping(target = "id", ignore = false)
    SupportProfileDTO supportToSupportProfileDTO(Support support);
}
