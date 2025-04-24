package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

}