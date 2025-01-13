package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<C extends User> extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByRoles(Roles role);
}