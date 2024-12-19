package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}