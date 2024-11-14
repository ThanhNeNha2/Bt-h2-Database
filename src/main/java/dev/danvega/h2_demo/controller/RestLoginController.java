package dev.danvega.h2_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.domain.UserDemo;
import dev.danvega.h2_demo.service.LoginService;
import dev.danvega.h2_demo.service.UserAuthService;

@RestController
@RequestMapping("/api")
public class RestLoginController {

    private final LoginService loginService;
    private final UserAuthService userAuthService;

    @Autowired
    public RestLoginController(LoginService loginService, UserAuthService userAuthService) {
        this.loginService = loginService;
        this.userAuthService = userAuthService;

    }

    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestBody User user) {
        // Kiểm tra người dùng đăng nhập
        boolean isAuthenticated = loginService.loginPostman(user.getUsername(), user.getPassword());

        System.out.println("isAuthenticated " + isAuthenticated);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            // Trả về lỗi khi đăng nhập thất bại
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> handleRegister(@RequestBody User user) {
        // Kiểm tra và đăng ký người dùng
        boolean isRegistered = loginService.register(user);

        if (isRegistered) {
            // Trả về mã trạng thái CREATED khi đăng ký thành công
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");
        } else {
            // Trả về lỗi khi tên đăng nhập đã tồn tại
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username already exists");
        }
    }

    @GetMapping("/userAuth")
    public String getHomePage(Model model) {
        List<User> users = this.userAuthService.getAllUser();
        return users.toString();
    }

}
