package com.example.ECommerce.Entities;

import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Entities.SubEntities.Customer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @CreatedDate
    @Column(name = "payment_date", nullable = false, updatable = false)
    private Date paymentDate;
    @Column(name = "order",unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private String order;
    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_holder_id")
    private Customer cardHolderUser;
    private Double amount;
}