package com.example.ECommerce.Documents;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String userId;
    private String productId;
    private int rating;
    private String comment;
    @CreatedDate
    private Date createdAt;
}
