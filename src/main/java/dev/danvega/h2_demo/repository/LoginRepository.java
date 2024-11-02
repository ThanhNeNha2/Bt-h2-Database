package dev.danvega.h2_demo.repository;

import dev.danvega.h2_demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    // Tìm kiếm user theo username
    User findByUsername(String username);
}