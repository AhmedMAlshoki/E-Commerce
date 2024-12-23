package com.example.ECommerce.Repositories.RoleBasedRepositories;

import com.example.ECommerce.Entities.SubEntities.Admin;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends SupportRepository {
}