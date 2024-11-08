package dev.danvega.h2_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.service.LoginService;

@RestController
@RequestMapping("/api")
public class RestLoginController {

    private final LoginService loginService;

    @Autowired
    public RestLoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody User user) {
        boolean isAuthenticated = loginService.login(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> handleRegister(@RequestBody User user) {
        boolean isRegistered = loginService.register(user);

        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
    }
}