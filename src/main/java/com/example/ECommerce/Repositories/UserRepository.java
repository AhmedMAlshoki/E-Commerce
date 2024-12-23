package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<C extends User> extends JpaRepository<User, Long> {
}