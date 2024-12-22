package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.Report;
import com.example.ECommerce.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "support_team")
@Inheritance(strategy = InheritanceType.JOINED)
public class Support extends User {
    @OneToMany
    private Set<Report> reportsSolved = new HashSet<>();
}
