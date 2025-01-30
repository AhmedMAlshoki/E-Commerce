package com.example.ECommerce.Controllers;

import com.example.ECommerce.DTOs.RoleBasedDTO.UserDTO;
import com.example.ECommerce.DTOs.UserLoginDTO;
import com.example.ECommerce.DTOs.UserRegisterationDTO;
import com.example.ECommerce.Entities.User;
import com.example.ECommerce.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/")
public class UserController {
    /*@GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard";
    }


     */
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserService userService , AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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
}
