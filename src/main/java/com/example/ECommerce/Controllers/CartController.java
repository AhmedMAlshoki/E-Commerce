package com.example.ECommerce.Controllers;

import com.example.ECommerce.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CartController {
    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PatchMapping("cart/product/{product_id}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long product_id,@RequestParam Long quantity) {
        try{
            return ResponseEntity.ok(cartService.addProduct(product_id, quantity));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("cart/product/{product_id}/remove")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long product_id) {
        try{
            return ResponseEntity.ok(cartService.removeProduct(product_id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("cart")
    public ResponseEntity<?> deleteCart() {
        try{
            return ResponseEntity.ok(cartService.deleteCart());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("cart")
    public ResponseEntity<?> getCart() {
        try{
            return ResponseEntity.ok(cartService.getCart());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("cart/order")
    public String order() {
        try{
            return cartService.orderCart();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

}