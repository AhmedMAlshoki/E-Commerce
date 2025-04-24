package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Report;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@NamedQuery(name = "report.toggleSolved", query = "UPDATE Report r SET r.isSolved = CASE WHEN r.isSolved = true THEN false ELSE true END WHERE r.id = :id")
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);

    List<Report> findByProductId(Long productId);

    List<Report> findByReportedUserId(Long userId);

    void toggleSolved(Long id);
}
