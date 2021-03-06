package com.DTeam.eshop.repositories;

import com.DTeam.eshop.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByUserEmail(String email);
    Customer findByUserEmail(String email);
}