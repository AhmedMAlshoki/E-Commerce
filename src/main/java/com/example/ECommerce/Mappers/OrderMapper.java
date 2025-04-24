package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.OrderDTO;
import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Services.AddressService;
import com.example.ECommerce.Services.PaymentsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, PaymentMapper.class})
@ComponentScan("com.example.ECommerce.Services.*")
public abstract class OrderMapper {

    @Autowired
    protected AddressService addressService;
    @Autowired
    protected PaymentsService paymentsService;
    @Mapping(target = "shippingAddress", expression = "java(addressService.getAddress(order.getShippingAddress()))")
    @Mapping(target = "payment", expression = "java(paymentsService.getPayment(order.getPaymentId()))")
    @Mapping(target = "destinationAddress", expression = "java(addressService.getAddress(order.getDestinationAddress()))")
    @Mapping(target = "id", ignore = false)
    public abstract OrderDTO orderToOrderDTO(Order order);
}
