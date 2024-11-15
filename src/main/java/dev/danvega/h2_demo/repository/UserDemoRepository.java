package dev.danvega.h2_demo.repository;

import dev.danvega.h2_demo.domain.UserDemo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDemoRepository extends JpaRepository<UserDemo, Long> {
    List<UserDemo> findAll();

    UserDemo save(UserDemo thanh);

    UserDemo findById(long id);

    UserDemo deleteById(long id);

}