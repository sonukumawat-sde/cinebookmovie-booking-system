package com.cinebook.controller;

import com.cinebook.model.User;
import com.cinebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Frontend connect karne ke liye zaroori hai
public class UserController {

    @Autowired
    private UserService userService;

    // API 1: Naya User Register karne ke liye
    // URL: POST http://localhost:8080/api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser); // 200 OK ke saath user data wapas
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration fail: " + e.getMessage());
        }
    }

    // API 2: Login karne ke liye
    // URL: POST http://localhost:8080/api/users/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");
            User user = userService.loginUser(email, password);
            return ResponseEntity.ok(user); // Login success hone par user detail wapas
        } catch (Exception e) {
            // Agar email ya password galat ho toh 401 Unauthorized error
            return ResponseEntity.status(401).body("Login fail: " + e.getMessage());
        }
    }
}