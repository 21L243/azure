package com.ey.mentor.mapper;

import com.ey.mentor.entity.Customer;
import com.ey.mentor.request.CustomerRequest;
import com.ey.mentor.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest req) {
        Customer c = new Customer();
        c.setName(req.getName());
        c.setEmail(req.getEmail());
        c.setPhone(req.getPhone());
        return c;
    }

    public CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(
                c.getId(),
                c.getName(),
                c.getEmail(),
                c.getPhone()
        );
    }
}
