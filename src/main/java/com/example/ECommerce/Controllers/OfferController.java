package com.example.ECommerce.Controllers;

import com.example.ECommerce.DTOs.OfferDTO;
import com.example.ECommerce.Services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }
    @PostMapping("/offer")
    public ResponseEntity<?> addOffer(@RequestBody OfferDTO offerDTO) {
        try {
            offerService.addOffer(offerDTO);
            return ResponseEntity.ok("Offer has been added successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/offer/{id}")
    public ResponseEntity<?> updateOffer(@RequestBody OfferDTO offerDTO) {
        try {
            offerService.updateOffer(offerDTO);
            return ResponseEntity.ok("Offer has been updated successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/offer/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        try {
            offerService.deleteOffer(id);
            return ResponseEntity.ok("Offer deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/offers")
    public ResponseEntity<?> getAllOffers() {
        try {
            return ResponseEntity.ok(offerService.getAllOffers());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<?> getOffer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(offerService.findOffer(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/product/{id}/offers")
    public ResponseEntity<?> getOffersByProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(offerService.getOffersByProduct(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}/offers")
    public ResponseEntity<?> getOffersByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(offerService.getOffersBySeller(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getActiveOffers")
    public ResponseEntity<?> getActiveOffers() {
        try {
            return ResponseEntity.ok(offerService.getActiveOffers());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
