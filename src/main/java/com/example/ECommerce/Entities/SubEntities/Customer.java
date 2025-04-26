package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Entities.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
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
@NamedEntityGraphs(
        {
                @NamedEntityGraph(name = "customerGraphAddress", attributeNodes = @NamedAttributeNode("personalAddress")),
                @NamedEntityGraph(name = "CustomerForProfile", attributeNodes = {@NamedAttributeNode("personalAddress"),
                                                                                 @NamedAttributeNode("purchasedProducts"),
                                                                                 @NamedAttributeNode("wishListedProducts") })


        }
)
@AllArgsConstructor
public class Customer extends User {
    @Nullable
    @OneToOne
    private Address personalAddress;
    private Double balance ;
    private Double amountSpent;
    private Double amountSaved;
    private Integer loyaltyPoints;
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

    @ElementCollection
    @Nullable
    List<String> reviewIds = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY)
    List<Report> reports = new ArrayList<>();

    public Customer() {
        super();
        Set<Product> purchasedProducts = new HashSet<>();
        Set<Product> wishListedProducts = new HashSet<>();
        List<String> orderIds = new ArrayList<>();
        List<String> reviewIds = new ArrayList<>();
        List<Report> reports = new ArrayList<>();
        this.purchasedProducts = purchasedProducts;
        this.wishListedProducts = wishListedProducts;
        this.orderIds = orderIds;
        this.reviewIds = reviewIds;
        this.reports = reports;
    }
}
