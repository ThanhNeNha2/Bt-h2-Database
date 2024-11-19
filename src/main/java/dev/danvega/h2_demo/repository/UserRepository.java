package dev.danvega.h2_demo.repository;

import org.springframework.data.repository.CrudRepository;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.domain.UserDemo;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    boolean existsByUsername(String username);
}
