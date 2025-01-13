package com.example.ECommerce.Entities;

import com.example.ECommerce.Enums.Categories;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Categories name ;
    @OneToMany
    private LinkedList<Product> products = new LinkedList<>();

}