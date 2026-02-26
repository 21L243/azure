package com.ey.mentor.service;

import com.ey.mentor.request.CustomerRequest;
import com.ey.mentor.response.CustomerResponse;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerResponse addCustomer(CustomerRequest request);

    Map<String,String> updateCustomer(Long id, CustomerRequest request);

    void deleteCustomer(Long id);

    CustomerResponse getCustomer(Long id);

    List<CustomerResponse> getAll();
}