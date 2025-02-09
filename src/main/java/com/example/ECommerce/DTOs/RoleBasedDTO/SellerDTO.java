package com.example.ECommerce.DTOs.RoleBasedDTO;

import com.example.ECommerce.DTOs.AddressDTO;

import java.util.Date;

public record SellerDTO(Long id,
                        String name,
                        String email,
                        Date createdAt,
                        String phoneNumber,
                        Double balance,
                        AddressDTO personalAddress,
                        String businessName,
                        AddressDTO shippingAddress)
        implements UserDTO {
}
