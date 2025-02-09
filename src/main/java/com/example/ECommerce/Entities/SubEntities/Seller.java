package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.Entities.Address;
import com.example.ECommerce.Entities.Offer;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "sellers")
@OnDelete(action = OnDeleteAction.CASCADE)
@NamedEntityGraphs(
        {
                 @NamedEntityGraph(name = "SellerWithProducts",attributeNodes = {@NamedAttributeNode("ownedProducts")}),
                 @NamedEntityGraph(name = "SellerWithOffers",attributeNodes = {@NamedAttributeNode("offers")}),
                @NamedEntityGraph(name = "ShippingAddress",attributeNodes = {@NamedAttributeNode("shippingAddress")})
        }
)
public class Seller extends Customer {
    private String businessName;
    private String taxId;
    @OneToOne
    private Address shippingAddress;
    @OneToMany
    private List<Product> ownedProducts = new ArrayList<>();
    @OneToMany
    private List<Offer> offers;// List of offers made by the seller <Offer>


}
