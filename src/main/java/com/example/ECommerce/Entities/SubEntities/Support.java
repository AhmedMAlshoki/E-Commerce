package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.Report;
import com.example.ECommerce.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;
@NamedEntityGraph(name = "support-entity-graph", attributeNodes = @NamedAttributeNode("reportsSolved"))
@Getter
@Setter
@Entity
@Table(name = "support_team")
@Inheritance(strategy = InheritanceType.JOINED)
@OnDelete(action = OnDeleteAction.CASCADE)
public class Support extends User {
    @OneToMany
    private Set<Report> reportsSolved = new HashSet<>();
}
