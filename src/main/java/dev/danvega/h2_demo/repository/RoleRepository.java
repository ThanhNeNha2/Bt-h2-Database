package dev.danvega.h2_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danvega.h2_demo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}