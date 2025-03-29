package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.PaymentDTO;
import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.Entities.Payment;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Mappers.PaymentMapper;
import com.example.ECommerce.Repositories.PaymentRepository;
import com.example.ECommerce.Services.UserServices.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentsService {

    private final CustomerService customerService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentsService(CustomerService customerService, PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.customerService = customerService;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    public Payment createPayment(Product product, Customer customer, int quantity,String orderID) throws RuntimeException {
        double totalCost = product.getPrice() * quantity;
        if (customer.getBalance() < totalCost) {
            throw new RuntimeException("Not enough balance to make the payment");
        }
        customer.setBalance(customer.getBalance() - totalCost);
        customer.getPurchasedProducts().add(product);
        customerService.updateCustomer(customer);
        Payment payment = new Payment();
        payment.setOrder(orderID);
        payment.setAmount(totalCost);
        payment.setCardHolderUser(customer);
        return payment;
    }

    @Transactional
    public void cancelPayment(String OrderId) {
        Payment payment = paymentRepository.findByOrder(OrderId);
        Customer customer = payment.getCardHolderUser();
        customer.setBalance(customer.getBalance() + payment.getAmount());
        customerService.updateCustomer(customer);
        paymentRepository.delete(payment);
    }

    public PaymentDTO getPayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow();
        return paymentMapper.paymentToPaymentDTO(payment);
    }

    public List<PaymentDTO> getPaymentsByUser(Long id) {
        List<Payment> payments = paymentRepository.findByCardHolderUser(id);
        return payments.stream().map(paymentMapper::paymentToPaymentDTO).toList();
    }
}
