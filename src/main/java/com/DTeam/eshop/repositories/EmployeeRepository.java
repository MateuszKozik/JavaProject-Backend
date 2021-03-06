package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Boolean existsByUserEmail(String email);
    Employee findByUserEmail(String email);
}