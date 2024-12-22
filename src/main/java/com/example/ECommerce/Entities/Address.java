package com.example.ECommerce.Entities;

import com.example.ECommerce.Enums.Country;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
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
    @Column(unique = true)
    private Country country;
    @Nullable
    private String zipCode;
}