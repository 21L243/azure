package com.ey.mentor.serviceimpl;

import com.ey.mentor.entity.Customer;
import com.ey.mentor.exception.ResourceNotFoundException;
import com.ey.mentor.mapper.CustomerMapper;
import com.ey.mentor.repository.CustomerRepository;
import com.ey.mentor.request.CustomerRequest;
import com.ey.mentor.response.CustomerResponse;
import com.ey.mentor.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private CustomerMapper mapper;

    @Override
    public CustomerResponse addCustomer(CustomerRequest req) {
        Customer customer = mapper.toEntity(req);
        return mapper.toResponse(repo.save(customer));
    }

    @Override
    public Map<String, String> updateCustomer(Long id, CustomerRequest req) {

        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id " + id + " not found"));

        Map<String, String> changes = new HashMap<>();

        if (!customer.getName().equals(req.getName())) {
            changes.put("name", customer.getName() + " → " + req.getName());
            customer.setName(req.getName());
        }

        if (!customer.getEmail().equals(req.getEmail())) {
            changes.put("email", customer.getEmail() + " → " + req.getEmail());
            customer.setEmail(req.getEmail());
        }

        if (!customer.getPhone().equals(req.getPhone())) {
            changes.put("phone", customer.getPhone() + " → " + req.getPhone());
            customer.setPhone(req.getPhone());
        }

        repo.save(customer);
        return changes;
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Customer id " + id + " not found");

        repo.deleteById(id);
    }

    @Override
    public CustomerResponse getCustomer(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id " + id + " not found"));

        return mapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}