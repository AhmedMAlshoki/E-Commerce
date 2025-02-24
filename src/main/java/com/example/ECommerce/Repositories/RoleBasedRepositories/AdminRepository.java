package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.DTOs.RoleBasedDTO.AdminDTO;
import com.example.ECommerce.Entities.SubEntities.Admin;
import com.example.ECommerce.Entities.SubEntities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

}