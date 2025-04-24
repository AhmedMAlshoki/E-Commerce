package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrder(String orderId);

    List<Payment> findByCardHolderUser(Long userId);

    List<Payment> findAfter(Date date);
}