package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.DTOs.ProductProfileDTO;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Enums.Categories;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SellerMapper.class
                                           , OfferMapper.class
})
public interface ProductMapper {

    //SellerServices to get seller id

    @Mapping(source = "owner", target = "seller")
    @Mapping(target = "category", source= "category.name")
    @Mapping(target = "seller",source = "owner.id")
    @Mapping(target = "id", ignore = false)
    ProductDTO productToProductDTO(Product product);


    @Mapping(target = "reviewIds", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "seller", target = "owner")
    @Mapping(source = "category", target= "category")
    @Mapping(target = "owner",ignore = true)
    Product productDTOToProduct(ProductDTO productDTO);

    @Mapping(source = "offer", target = "offer")
    @Mapping(target = "category", source= "category")
    @Mapping(target = "seller",source = "owner")
    ProductProfileDTO productToProductProfileDTO(Product product);

    default Categories mapStringToCategory(String category) {
        return Categories.valueOf(category.toUpperCase()); // Case-insensitive
    }

    // Custom logic to convert Categories enum to String
    default String mapCategoryToString(Categories category) {
        return category.name();
    }

}
