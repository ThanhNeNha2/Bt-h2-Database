package dev.danvega.h2_demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.domain.UserDemo;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.UserDemoService;

@Controller
public class CompanyController {
    private final UserDemoService userService;
    private final CompanyService companyService;

    public CompanyController(UserDemoService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;

    }

    @GetMapping("/addCompany")
    public String add(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany"; // Tên trang Thymeleaf cho form thêm công ty
    }

    @PostMapping("/addCompany")
    public String saveOrUpdate(@ModelAttribute("company") Company company) {

        // Giả sử bạn muốn lấy tất cả người dùng để gán vào công ty
        List<UserDemo> users = userService.getAllUser();
        company.setUsers(users); // Gán danh sách người dùng cho công ty

        companyService.saveOrUpdate(company); // Lưu hoặc cập nhật công ty

        return "redirect:/companies"; // Chuyển hướng về trang danh sách công ty sau khi lưu
    }

    @GetMapping("/companies")
    public String trangChiTiet(Model model) {
        List<Company> companies = companyService.getAll();
        System.out.println("companies: " + companies); // Để kiểm tra danh sách công ty trong console

        model.addAttribute("companies", companies); // Truyền danh sách công ty vào model
        return "companies"; // Tên trang Thymeleaf cho danh sách công ty
    }
}
