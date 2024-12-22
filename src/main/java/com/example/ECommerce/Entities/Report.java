package com.example.ECommerce.Entities;

import com.example.ECommerce.Enums.Report_Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String description;
    @Enumerated(EnumType.STRING)
    private Report_Category reportCategory;
    Boolean isSolved;

}