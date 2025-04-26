package com.example.ECommerce.Repositories.JPA.RoleBasedRepositories;

import com.example.ECommerce.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrder(String orderId);

    @Query("SELECT p FROM Payment p WHERE p.cardHolderUser.id = :userId")
    List<Payment> findByCardHolderUser(Long userId);

    @Query("SELECT p FROM Payment p WHERE p.paymentDate > :date")
    List<Payment> findAfter(Date date);
}