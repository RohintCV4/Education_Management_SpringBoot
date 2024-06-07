package com.example.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import com.example.edu.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
}
