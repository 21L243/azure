package com.ey.mentor.controller;

import com.ey.mentor.request.CustomerRequest;
import com.ey.mentor.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @PostMapping
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerservice.addCustomer(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest req) {
        return ResponseEntity.ok(customerservice.updateCustomer(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerservice.deleteCustomer(id);
        return ResponseEntity.ok("Customer id "+id+" deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerservice.getCustomer(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerservice.getAll());
    }

}


