package com.example.ECommerce.Controllers.RolesControllers;


import com.example.ECommerce.Services.UserServices.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SupportController {
    private final SupportService supportService;

    @Autowired
    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @GetMapping("/supports")
    public ResponseEntity<?> getAllSupports() {
        try {
            return ResponseEntity.ok(supportService.getAllSupports());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/support/{id}")
    public ResponseEntity<?> getSupport(Long id) {
        try {
            return ResponseEntity.ok(supportService.getSupport(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
