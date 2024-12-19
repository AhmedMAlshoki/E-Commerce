package com.example.ECommerce.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    @CreatedDate
    @Column(name = "payment_date", nullable = false, updatable = false)
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "card_holder_id")
    private User cardHolderName;
    private int amount;
}