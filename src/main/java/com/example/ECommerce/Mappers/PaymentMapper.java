package com.example.ECommerce.Mappers;



import com.example.ECommerce.DTOs.PaymentDTO;
import com.example.ECommerce.Entities.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderMapper.class,CustomerMapper.class})
public interface PaymentMapper {


    @Mapping(target = "order", expression = "java(orderService.getOrder(payment.getOrder().getId()))")
    @Mapping(target = "cardHolderUser", source = "cardHolderUser")
    PaymentDTO paymentToPaymentDTO(Payment payment);
}
