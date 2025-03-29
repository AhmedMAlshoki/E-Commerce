package com.example.ECommerce.Controllers;


import com.example.ECommerce.DTOs.ProductDTO;
import com.example.ECommerce.DTOs.ProductProfileDTO;
import com.example.ECommerce.Enums.Categories;
import com.example.ECommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{category}")
    public ResponseEntity<?> getProductsByCategory(String category) {
        Categories categoryEnum = Categories.valueOf(category.toUpperCase());
        List<ProductDTO> products = productService.getProductsByCategory(categoryEnum);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(Long id) {
        try {
            ProductDTO product = productService.getProduct(id);
            return ResponseEntity.ok(product);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            productService.updateProduct(productDTO);
                return ResponseEntity.ok("product has been updated successfully , refresh the endpoint to get the updated data");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.addProduct(productDTO);
            return ResponseEntity.ok("Product has been added successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/product/{id}/profile")
    public ResponseEntity<?> getProductProfile(@PathVariable Long id) {
        try {
            ProductProfileDTO product = productService.getProductProfile(id);
            return ResponseEntity.ok(product);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/seller/{id}}/products")
    public ResponseEntity<?> getUserProducts(@PathVariable Long id) {
        try {
            List<ProductDTO> products = productService.getProductsBySeller(id);
            return ResponseEntity.ok(products);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/product/price/")
    public ResponseEntity<?> getProductsByPriceOrGreaterThan(@RequestParam  Double balance) {
        List<ProductDTO> products = productService.getProductsByPriceOrGreaterThan(balance);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/category/{category}/price/")
    public ResponseEntity<?> getProductsWithCategoryBetween(@PathVariable String category,@RequestParam Double minBalance,@RequestParam Double maxBalance) {
        Categories categoryEnum = Categories.valueOf(category.toUpperCase());
        List<ProductDTO> products = productService.getProductsWithCategoryBetween(categoryEnum,minBalance,maxBalance);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/category/{category}/offer")
    public ResponseEntity<?> getProductsByCategoryNameANDHasOffer(@PathVariable String category) {
        Categories categoryEnum = Categories.valueOf(category.toUpperCase());
        List<ProductDTO> products = productService.getProductsByCategoryNameANDHasOffer(categoryEnum);
        return ResponseEntity.ok(products);
    }

}
