package dev.danvega.h2_demo.repository;

import org.springframework.data.repository.CrudRepository;

import dev.danvega.h2_demo.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}

