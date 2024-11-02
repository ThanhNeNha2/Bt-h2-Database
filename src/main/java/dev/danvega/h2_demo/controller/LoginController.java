package dev.danvega.h2_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import dev.danvega.h2_demo.domain.User;

@Controller
public class LoginController {
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
}
