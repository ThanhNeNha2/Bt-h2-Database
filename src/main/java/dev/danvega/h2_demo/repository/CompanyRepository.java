package dev.danvega.h2_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.danvega.h2_demo.domain.Company;
import dev.danvega.h2_demo.domain.UserDemo;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();

    Company findById(long id);
}