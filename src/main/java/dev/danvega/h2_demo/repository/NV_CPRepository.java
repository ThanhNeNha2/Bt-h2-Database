package dev.danvega.h2_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danvega.h2_demo.domain.NV_CP;

public interface NV_CPRepository extends JpaRepository<NV_CP, Long> {

}