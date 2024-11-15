package dev.danvega.h2_demo.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.domain.UserDemo;
import dev.danvega.h2_demo.service.CompanyService;
import dev.danvega.h2_demo.service.UserDemoService;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyController {

    private final UserDemoService userService;
    private final CompanyService companyService;

    public RestCompanyController(UserDemoService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    // Lấy danh sách công ty
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAll(); // Trả về danh sách công ty
    }

    // Thêm một công ty mới
    @PostMapping(consumes = "application/json")
    public Company addCompany(@RequestBody Company company) {
        // Gán danh sách người dùng cho công ty
        List<UserDemo> users = userService.getAllUser();
        company.setUsers(users); // Gán danh sách người dùng cho công ty
        return companyService.saveOrUpdate(company); // Lưu công ty và trả về công ty đã lưu
    }
}