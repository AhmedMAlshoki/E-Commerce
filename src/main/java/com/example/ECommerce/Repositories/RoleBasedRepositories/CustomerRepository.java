package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository<S extends Customer> extends UserRepository<Customer> {
}