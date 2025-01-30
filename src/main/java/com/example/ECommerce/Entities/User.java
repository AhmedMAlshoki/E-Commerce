package com.example.ECommerce.Entities;

import com.example.ECommerce.Documents.Review;
import com.example.ECommerce.Enums.Roles;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Setter
    @Getter
    private String username;
    private String email;
    private String password;
    @Nullable
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Roles role;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;


}