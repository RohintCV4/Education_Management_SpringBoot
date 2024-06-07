package com.example.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
