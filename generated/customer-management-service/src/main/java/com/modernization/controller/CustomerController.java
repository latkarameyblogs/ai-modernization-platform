package com.modernization.controller;

import com.modernization.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public static class CustomerRequest {
        @NotBlank(message = "First name is mandatory")
        @Size(max = 50)
        public String firstName;

        @NotBlank(message = "Last name is mandatory")
        @Size(max = 50)
        public String lastName;

        @NotNull(message = "Age must be provided")
        public Integer age;
    }

    public static class CustomerResponse {
        public String customerNumber;
        public String status;
        public String message;

        public CustomerResponse(String customerNumber, String status, String message) {
            this.customerNumber = customerNumber;
            this.status = status;
            this.message = message;
        }
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        try {
            String customerNumber = customerService.createCustomer(request.firstName, request.lastName, request.age);
            CustomerResponse response = new CustomerResponse(customerNumber, "SUCCESS", "Customer created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            CustomerResponse response = new CustomerResponse(null, "ERROR", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
