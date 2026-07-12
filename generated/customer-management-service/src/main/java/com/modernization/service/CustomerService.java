package com.modernization.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    public String createCustomer(String firstName, String lastName, Integer age) {
        // Minimal stub implementation that simulates customer number generation
        // In real scenario, would integrate with GENACUSTNUM Counter Service and persist to PostgreSQL

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        if (age == null || age < 0) {
            throw new IllegalArgumentException("Age must be a positive integer");
        }

        // Generate unique customer number (UUID used here as placeholder)
        return "CUST-" + UUID.randomUUID().toString();
    }
}
