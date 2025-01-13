package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "sellers")
public class Seller extends Customer {
    private String businessName;
    private String taxId;
    @OneToOne
    private Address shippingAddress;
    @OneToMany
    @ElementCollection
    private List<Product> ownedProducts = new ArrayList<>();
    @OneToMany
    @ElementCollection
    private List<Offer> offers; // List of offers made by the seller <Offer>
}
