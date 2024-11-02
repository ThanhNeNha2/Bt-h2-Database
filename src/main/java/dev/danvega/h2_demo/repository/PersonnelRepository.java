package dev.danvega.h2_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import dev.danvega.h2_demo.domain.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    List<Personnel> findAll();

    Personnel save(Personnel personnel);

}