package com.example.ECommerce.Entities;

import com.example.ECommerce.Entities.SubEntities.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;

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
    private Date startDate = new Date(Date.from(new Date().toInstant()).getTime());
    private Date endDate = new Date(Date.from(new Date().toInstant()).getTime() + 24 * 60 * 60 * 1000);
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}