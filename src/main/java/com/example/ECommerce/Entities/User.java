package com.example.ECommerce.Entities;
import com.example.ECommerce.Enums.Roles;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;
    @Setter
    @Getter
    protected String username;
    protected String email;
    protected String password;
    @Nullable
    protected String phoneNumber;

    @Enumerated(EnumType.STRING)
    protected Roles role;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Date createdAt;
}