package com.repository;

import com.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Method to retrieve all customers
    List<Customer> findAll();

    // Method to retrieve a customer by their ID
    Optional<Customer> findById(Long id);
}
