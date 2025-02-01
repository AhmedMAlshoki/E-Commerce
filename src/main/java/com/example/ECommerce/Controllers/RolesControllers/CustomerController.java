package com.example.ECommerce.Controllers.RolesControllers;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ECommerce.Services.UserServices.CustomerService;
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final  CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(Long id, CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("product/{productId}/buyers")
    public ResponseEntity<?> getBuyers(Long productId) {
        try {
            return ResponseEntity.ok(customerService.getBuyers(productId));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    /*
    @Get customerByAddeess
    @Get Customer
    */
     @GetMapping("customer/byBalance")
    public ResponseEntity<?> getCustomersByBalanceGreaterThanOrEqual(@RequestParam double balance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceGreaterThanOrEqual(balance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("customer/byBalanceReverse")
    public ResponseEntity<?> getCustomersByBalanceLowerThanOrEqual(@RequestParam double balance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceLessThanOrEqual(balance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("customer/inBetweenBalance")
    public ResponseEntity<?> getCustomersByBalanceBetween(@RequestParam double minBalance, @RequestParam double maxBalance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceBetween(minBalance, maxBalance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("customers")
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
