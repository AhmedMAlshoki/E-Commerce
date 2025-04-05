package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.ReviewDTO;
import com.example.ECommerce.Documents.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "product.id")
    Review reviewDTOToReview(ReviewDTO reviewDTO);

    @Mapping(target = "id", ignore = false)
    @Mapping(target = "user", expression = "java(customerService.getCustomer(review.getUserID()))")
    @Mapping(target = "product", expression = "java(productService.getProduct(review.getProductID()))")
    ReviewDTO reviewToReviewDTO(Review review);
}
