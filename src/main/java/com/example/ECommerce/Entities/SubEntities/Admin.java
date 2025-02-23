package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@NamedEntityGraph(name = "support-entity-graph", attributeNodes = @NamedAttributeNode("reportsSolved"))
@Getter
@Setter
@Entity
@Table(name = "admins")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Admin extends Support {

}
