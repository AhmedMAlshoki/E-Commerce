package com.example.ECommerce.Controllers.RolesControllers;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_SELLER')")
    public ResponseEntity<?> getCustomersByBalanceGreaterThanOrEqual(@RequestParam double balance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceGreaterThanOrEqual(balance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("customer/byBalanceReverse")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
    public ResponseEntity<?> getCustomersByBalanceLowerThanOrEqual(@RequestParam double balance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceLessThanOrEqual(balance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("customer/inBetweenBalance")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getCustomersByBalanceBetween(@RequestParam double minBalance, @RequestParam double maxBalance) {
        try {
            return ResponseEntity.ok(customerService.getCustomerWithBalanceBetween(minBalance, maxBalance));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("customers")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
