package com.example.stdManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stdManagement.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
	
	Optional<School> findByEmail(String email);

	boolean existsByEmail(String email);

}
