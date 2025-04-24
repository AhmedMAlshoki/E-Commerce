package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}