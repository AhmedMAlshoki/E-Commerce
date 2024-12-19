package com.example.ECommerce.Repositories;

import com.example.ECommerce.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}