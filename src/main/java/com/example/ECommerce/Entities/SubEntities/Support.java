package com.example.ECommerce.Entities.SubEntities;

import com.example.ECommerce.Entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "support_team")
public class Support extends User {
}
