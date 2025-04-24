package com.example.ECommerce.Mappers;



import com.example.ECommerce.DTOs.PaymentDTO;
import com.example.ECommerce.Entities.Payment;
import com.example.ECommerce.Services.OrderService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@Mapper(componentModel = "spring", uses = {OrderMapper.class,CustomerMapper.class})
@ComponentScan("com.example.ECommerce.Services.*")
public abstract class PaymentMapper {

    @Autowired
    protected OrderService orderService;

    @Mapping(target = "order", expression = "java(orderService.getOrder(payment.getOrder()))")
    @Mapping(target = "cardHolderUser", source = "cardHolderUser")
    @Mapping(target = "id", ignore = false)
    public abstract PaymentDTO paymentToPaymentDTO(Payment payment);
}
