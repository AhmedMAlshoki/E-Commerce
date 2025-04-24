package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    LinkedList<User> findByRole(Roles role);

    //save new user
    User save(UserRegisterationDTO user);

    void deleteByUsername(String username);

    long count();

    long countByRole(Roles role);

}