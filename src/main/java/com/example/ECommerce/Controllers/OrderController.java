package com.example.ECommerce.Controllers;



import com.example.ECommerce.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/order/{product_id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> addOrder(Long product_id, @RequestParam int quantity) {
        try {
            return ResponseEntity.ok(orderService.addOrder(product_id, quantity));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity.ok(orderService.getAllOrders());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT') or @ProductService.isOwner(#id)")
    public ResponseEntity<?> getAllOrdersPerProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersByProduct(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT') or @OrderService.isOwner(#id)")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        try {
            return ResponseEntity.ok(orderService.getOrder(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/order/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT') or @OrderService.isOwner(#id)")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @GetMapping("/order/user/{id}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrdersByUser(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
