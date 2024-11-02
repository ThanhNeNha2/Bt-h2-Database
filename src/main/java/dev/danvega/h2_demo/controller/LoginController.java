package dev.danvega.h2_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.service.LoginService;

@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User()); // Tạo đối tượng User rỗng

        return "Login"; // Trả về trang login.html
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user) {

        System.out.println("request submit " + user);

        return "redirect:/"; // Chuyển hướng về trang chủ
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User()); // Tạo đối tượng User rỗng
        return "Register"; // Trả về trang register.html
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("user") User user, Model model) {
        // Kiểm tra và đăng ký người dùng
        if (loginService.register(user)) {
            return "redirect:/login"; // Chuyển hướng về trang chủ nếu thành công
        } else {
            model.addAttribute("errorMessage", "Username already exists. Please try a different one.");
            return "Register"; // Quay lại trang đăng ký với thông báo lỗi
        }
    }
}
