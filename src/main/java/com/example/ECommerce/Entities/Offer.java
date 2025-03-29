package com.example.ECommerce.Entities;

import com.example.ECommerce.Entities.SubEntities.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "offerWithSeller", attributeNodes = {
        @NamedAttributeNode("seller")
    }),
    @NamedEntityGraph(name = "offerWithProduct", attributeNodes = {
        @NamedAttributeNode("product")
    }),
        @NamedEntityGraph(name = "offerWithProductAndSeller", attributeNodes = {
                @NamedAttributeNode("product"),
                @NamedAttributeNode("seller")
        })

})
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Seller seller;
    private double discount=0.0;
    private LocalDate startDate = new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    private LocalDate endDate = startDate.plusMonths(1);
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}