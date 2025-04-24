package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.DTOs.ProductProfileDTO;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Enums.Categories;

import com.example.ECommerce.Services.UserServices.SellerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@Mapper(componentModel = "spring", uses = {SellerMapper.class
                                           , OfferMapper.class
})
@ComponentScan("com.example.ECommerce.*")
public abstract class ProductMapper {

    //SellerServices to get seller id

    @Autowired
    protected SellerService sellerService;

    @Mapping(source = "owner.id", target = "seller")
    @Mapping(target = "category", source= "category")
    @Mapping(target = "id", ignore = false)
    public abstract ProductDTO  productToProductDTO(Product product);



    @Mapping(target = "reviewIds", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner",  expression = "java(sellerService.getSellerEntity(productDTO.seller()))")
    @Mapping(source = "category", target= "category")
    public abstract Product productDTOToProduct(ProductDTO productDTO);

    @Mapping(source = "offer", target = "offer")
    @Mapping(target = "category", source= "category")
    @Mapping(target = "seller",source = "owner")
    public abstract ProductProfileDTO productToProductProfileDTO(Product product);

    Categories mapStringToCategory(String category) {
        return Categories.valueOf(category.toUpperCase()); // Case-insensitive
    }

    // Custom logic to convert Categories enum to String
    String mapCategoryToString(Categories category) {
        return category.name();
    }

}
