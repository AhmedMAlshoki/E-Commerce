package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedEntityGraph(name = "customerGraphAddress", attributeNodes = @NamedAttributeNode("personalAddress"))
public class Customer extends User {
    @Nullable
    @OneToOne
    private Address personalAddress;
    private Double balance = 0.0;
    private Double amountSpent = 0.0;
    private Double amountSaved = 0.0;
    private Integer loyaltyPoints = 0;
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
    @Nullable
    private List<String> orderIds = new ArrayList<>();

    @OneToMany
    private Set<Cart> carts = new HashSet<>();

    Boolean isSeller = false;

    @ElementCollection
    @Nullable
    List<String> reviewIds = new ArrayList<>();


    @OneToMany
    List<Report> reports = new ArrayList<>();
}
