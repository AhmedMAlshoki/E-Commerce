package com.example.ECommerce.Entities;

import com.example.ECommerce.Enums.Country;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Nullable
    private String zipCode;
}