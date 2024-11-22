package dev.danvega.h2_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danvega.h2_demo.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User save(User thanh);

    User findById(long id);

    User deleteById(long id);
}
