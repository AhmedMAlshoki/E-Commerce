package com.example.ECommerce.Mappers;

import com.example.ECommerce.DTOs.OrderDTO;
import com.example.ECommerce.Documents.Order;
import com.example.ECommerce.Services.AddressService;
import com.example.ECommerce.Services.PaymentsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "shippingAddress", expression = "java(addressService.getAddress(order.getShippingAddress()))")
    @Mapping(target = "payment", expression = "java(paymentsService.getPayment(order.getPaymentId()))")
    @Mapping(target = "destinationAddress", expression = "java(addressService.getAddress(order.getDestinationAddress()))")
    OrderDTO orderToOrderDTO(Order order);
}
