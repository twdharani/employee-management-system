package com.dharani.employeemanagementsystem.repository;

import com.dharani.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByAadhar(String aadhar);
    Optional<Employee> findByAadhar(String aadhar);
}