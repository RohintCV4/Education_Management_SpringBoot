package com.example.stdManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stdManagement.entity.Course;
import com.example.stdManagement.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

}
