package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrder(String orderId);

    List<Payment> findByCardHolderUser(Long userId);

    List<Payment> findAfter(Date date);
}