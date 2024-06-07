package com.example.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.edu.entity.Course;
import com.example.edu.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

}
