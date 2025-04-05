package com.example.ECommerce.Repositories;

import com.example.ECommerce.Documents.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(Long userId);
}
