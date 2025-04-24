package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.ReviewDTO;
import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Services.ProductService;
import com.example.ECommerce.Services.UserServices.CustomerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@Mapper(componentModel = "spring",uses = {CustomerMapper.class,ProductMapper.class})
@ComponentScan("com.example.ECommerce.Services.*")
public abstract class ReviewMapper {

    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected ProductService productService;

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "product.id")
    public abstract Review reviewDTOToReview(ReviewDTO reviewDTO);

    @Mapping(target = "id", ignore = false)
    @Mapping(target = "user", expression = "java(customerService.getCustomer(review.getUserId()))")
    @Mapping(target = "product", expression = "java(productService.getProduct(review.getProductId()))")
    public abstract ReviewDTO reviewToReviewDTO(Review review);
}
