package com.example.ECommerce.Controllers;


import com.example.ECommerce.Services.PaymentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentsService paymentsService;

    public PaymentController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }


    @GetMapping("payment/{id}")
    public ResponseEntity<?> getPayment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(paymentsService.getPayment(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("user/{id}/payments")
    public ResponseEntity<?> getPaymentsByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(paymentsService.getPaymentsByUser(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
