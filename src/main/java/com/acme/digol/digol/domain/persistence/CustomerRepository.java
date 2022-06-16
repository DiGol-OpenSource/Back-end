package com.acme.digol.digol.domain.persistence;

import com.acme.digol.digol.domain.model.entity.Customer;
import com.acme.digol.digol.domain.model.entity.SportField;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDni(String dni);
    Customer findByName(String name);
}
