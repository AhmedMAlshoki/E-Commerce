package com.example.ECommerce.Entities;

import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Entities.SubEntities.Seller;
import com.example.ECommerce.Enums.Categories;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
//@NamedEntityGraph(name = "ProductOwner", attributeNodes = @NamedAttributeNode("owner"))
//@NamedEntityGraph(name = "ProductOffer", attributeNodes = @NamedAttributeNode("offer"))
@NamedEntityGraphs({
    @NamedEntityGraph(name = "ProductOwner", attributeNodes = {@NamedAttributeNode("owner")}),
    @NamedEntityGraph(name = "ProductOfferAndOwner", attributeNodes = {@NamedAttributeNode("offer"), @NamedAttributeNode("owner")})
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private double price=0.0;
    private int quantity=1;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private Categories category;
    @ManyToOne
    @CreatedBy
    @JoinColumn(name = "user_id")
    private Seller owner;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @ElementCollection
    List<String> reviewIds = new ArrayList<>();
    @OneToOne
    private Offer offer;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;


}