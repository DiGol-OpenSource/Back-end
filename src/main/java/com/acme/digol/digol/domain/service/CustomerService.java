package com.acme.digol.digol.domain.service;

import com.acme.digol.digol.domain.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Page<Customer> getAll(Pageable pageable);
    Customer getById(Long customerId);
    Customer create(Customer customer);
    Customer update(Long customerId, Customer request);
    ResponseEntity<?> delete(Long customerId);
}
