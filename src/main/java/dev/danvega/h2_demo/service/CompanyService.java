package dev.danvega.h2_demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.domain.Personnel;
import dev.danvega.h2_demo.repository.CompanyRepository;
import dev.danvega.h2_demo.repository.UserDemoRepository;

@Service
public class CompanyService {
    private final UserDemoRepository userRepository;
    private final CompanyRepository companyRepository;

    public CompanyService(UserDemoRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Company saveOrUpdate(Company company) {
        return this.companyRepository.save(company);
    }

    // Lấy Company theo ID
    public Company getById(long id) {
        return this.companyRepository.findById(id);
    }

    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

}
