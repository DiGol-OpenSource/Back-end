package com.acme.digol.digol.service;
import com.acme.digol.digol.domain.model.entity.Customer;
import com.acme.digol.digol.domain.persistence.CustomerRepository;
import com.acme.digol.digol.domain.service.CustomerService;
import com.acme.digol.shared.exception.ResourceNotFoundException;
import com.acme.digol.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String ENTITY ="Customer";
    private final CustomerRepository customerRepository;
    private final Validator validator;

    public CustomerServiceImpl(CustomerRepository customerRepository, Validator validator){
        this.customerRepository=customerRepository;
        this.validator=validator;
    }
    @Override
    public List<Customer> getAll(){return customerRepository.findAll();}

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
    @Override
    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public Customer create(Customer customer) {

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        Customer customerWithName = customerRepository.findByName(customer.getName());

        if (customerWithName != null)
            throw new ResourceValidationException(ENTITY,
                    "An customer with the same name already exists.");

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long customerId, Customer request) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name Uniqueness validation

        Customer customerWithName = customerRepository.findByName(request.getName());

        if (customerWithName != null && !customerWithName.getId().equals(customerId))
            throw new ResourceValidationException(ENTITY,
                    "An customer with the same name already exists.");

        return customerRepository.findById(customerId).map(student ->
                        customerRepository.save(student.withName(request.getName())
                                .withPhoneNumber(request.getPhoneNumber())
                                .withDni(request.getDni())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }

    @Override
    public ResponseEntity<?> delete(Long customerId) {
        return customerRepository.findById(customerId).map(student -> {
            customerRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, customerId));
    }
}
