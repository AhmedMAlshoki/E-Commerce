package com.example.ECommerce.Entities;

import com.example.ECommerce.Entities.SubEntities.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Seller seller;
    @ColumnDefault("0")
    private double discount;
    private String startDate;
    private String endDate;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}