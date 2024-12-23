package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User {
    /**
     *@EntityGraph(attributePaths = "personalAddress")
     */

    @OneToOne
    private Address personalAddress;
    private Double balance;
    private Double amountSpent;
    private Double amountSaved;
    private Integer LoyaltyPoints;
    @ManyToMany
    //EntityGraph
    @JoinTable(
            name = "customer_purchased_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> purchasedProducts = new HashSet<>();
    @ManyToMany
    /*EntityGraph
    @EntityGraph(attributePaths = {"purchasedProducts"})
     */
    @JoinTable(
            name = "customer_wishlist_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishListedProducts = new HashSet<>();
    @ElementCollection
    private List<String> orderIds = new ArrayList<>();

    @OneToMany
    private Set<Cart> carts = new HashSet<>();

    @ElementCollection
    List<String> reviewIds = new ArrayList<>();

    @OneToMany
    List<Report> reports = new ArrayList<>();
}
