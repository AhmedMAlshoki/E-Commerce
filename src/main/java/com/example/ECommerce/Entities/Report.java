package com.example.ECommerce.Entities;

import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Enums.Report_Category;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "report")
@EntityListeners(AuditingEntityListener.class)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @CreatedBy
    private Customer user;
    @Nullable
    @ManyToOne
    private Product product;
    @Nullable
    @ManyToOne
    private Customer reportedUser;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Report_Category reportCategory;
    boolean isSolved=false;

}