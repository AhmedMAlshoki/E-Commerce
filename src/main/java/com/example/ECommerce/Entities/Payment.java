package com.example.ECommerce.Entities;

import com.example.ECommerce.Documents.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    private String cardNumber;
    private Date expireDate;
    private String cvv;
    @CreatedDate
    @Column(name = "payment_date", nullable = false, updatable = false)
    private Date paymentDate;
    private Long order;
    @ManyToOne
    @JoinColumn(name = "card_holder_id")
    private User cardHolderUser;
    private Integer amount;
}