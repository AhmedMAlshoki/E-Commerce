package com.example.ECommerce.Controllers;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Services.UserServices.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    /*@GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard";
    }
/api/v1/hello

     */
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterationDTO userRegisterationDTO)  {
        try {
              UserDTO userDTO = userService.registerUser(userRegisterationDTO);
              return ResponseEntity.ok(userDTO);
            }
        catch (Exception e) {
              return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("role/{role}")
    public ResponseEntity<List<User>> getRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.getRoleUsers(role));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody JsonNode jsonObject) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, jsonObject));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
