package com.example.ECommerce.Mappers;

import com.example.ECommerce.Services.AddressService;
import com.example.ECommerce.Services.OrderService;
import com.example.ECommerce.Services.PaymentsService;

public class MappingContext {
    private final PaymentsService paymentService;
    private final AddressService addressService;
    private final OrderService orderService;
    public MappingContext(PaymentsService paymentService, AddressService addressService, OrderService orderService) {
        this.paymentService = paymentService;
        this.addressService = addressService;
        this.orderService = orderService;
    }
}
