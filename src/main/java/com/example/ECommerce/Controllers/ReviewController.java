package com.example.ECommerce.Controllers;


import com.example.ECommerce.DTOs.ReviewDTO;
import com.example.ECommerce.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            return ResponseEntity.ok(reviewService.addReview(reviewDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> getReview(@PathVariable String id) {
        try {
            return ResponseEntity.ok(reviewService.getReview(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/review/{id}")
    public ResponseEntity<?> updateReview(@PathVariable String id, @RequestBody ReviewDTO reviewDTO) {
        try {
            return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable String id) {
        try {
            return ResponseEntity.ok(reviewService.deleteReview(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reviews/{product_id}")
    public ResponseEntity<?> getReviewsByProduct(@PathVariable Long product_id) {
        try {
            return ResponseEntity.ok(reviewService.getReviewsByProduct(product_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}/reviews")
    public ResponseEntity<?> getReviewsByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getReviewsByUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
