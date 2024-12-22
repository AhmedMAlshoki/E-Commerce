package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    private List<Product> ownedProducts = new ArrayList<>();
    @OneToMany
    private List<Offer> offers; // List of offers made by the seller <Offer>
}
