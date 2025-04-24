package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.ReviewDTO;
import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Mappers.ReviewMapper;
import com.example.ECommerce.Repositories.Mongo.ReviewRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public String addReview(ReviewDTO reviewDTO) {
        reviewRepository.save(reviewMapper.reviewDTOToReview(reviewDTO));
        return "Review Added";
    }

    public ReviewDTO getReview(String id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        return reviewMapper.reviewToReviewDTO(review);
    }

    public ReviewDTO updateReview(String id, ReviewDTO reviewDTO) throws Exception {
        boolean exists = reviewRepository.existsById(id);
        if (exists) {
            return reviewMapper.reviewToReviewDTO(reviewRepository.save(reviewMapper.reviewDTOToReview(reviewDTO)));
        }
        else {
            throw new Exception("Review does not exist");
        }
    }

    public String deleteReview(String id) throws Exception {
        boolean exists = reviewRepository.existsById(id);
        if (exists) {
            reviewRepository.deleteById(id);
            return "Review is deleted";
        }
        else {
            return "Review is not found";
        }

    }

    public boolean isOwner(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        Long user_id = userDetails.getId();
        Long owner_id = reviewRepository.findById(id).orElseThrow().getUserId();
        return user_id.equals(owner_id);
    }
    public  List<ReviewDTO>getReviewsByProduct(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(reviewMapper::reviewToReviewDTO).toList();
    }

    public List<ReviewDTO> getReviewsByUser(Long id) {
        List<Review> reviews = reviewRepository.findByUserId(id);
        return reviews.stream().map(reviewMapper::reviewToReviewDTO).toList();
    }
}
