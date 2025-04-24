package com.example.ECommerce.Mappers;

import com.example.ECommerce.Services.AddressService;
import com.example.ECommerce.Services.OrderService;
import com.example.ECommerce.Services.PaymentsService;
import com.example.ECommerce.Services.ProductService;
import com.example.ECommerce.Services.UserServices.CustomerService;
import com.example.ECommerce.Services.UserServices.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingContext {
    private final PaymentsService paymentService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final SellerService sellerService;

    @Autowired
    public MappingContext(PaymentsService paymentService, AddressService addressService, OrderService orderService,
                          CustomerService customerService, ProductService productService, SellerService sellerService) {
        this.paymentService = paymentService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.sellerService = sellerService;
    }
}
