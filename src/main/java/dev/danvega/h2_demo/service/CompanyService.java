package dev.danvega.h2_demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.repository.CompanyRepository;
import dev.danvega.h2_demo.repository.UserRepository;

@Service
public class CompanyService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public CompanyService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Company saveOrUpdate(Company company) {
        return this.companyRepository.save(company);
    }

    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

}
