package com.example.ECommerce.DTOs;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;

import java.util.Date;

public record PaymentDTO(Long id , CustomerDTO cardHolderUser , Double amount
    , Date paymentDate , OrderDTO order) {
}
