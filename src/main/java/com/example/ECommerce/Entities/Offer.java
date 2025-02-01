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
    private String startDate = new Date(Date.from(new Date().toInstant()).getTime()).toString();
    private String endDate = new Date(Date.from(new Date().toInstant()).getTime() + 24 * 60 * 60 * 1000).toString();
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}