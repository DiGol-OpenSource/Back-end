package com.acme.digol.digol.mapping;

import com.acme.digol.digol.domain.model.entity.Customer;

import com.acme.digol.digol.resource.*;
import com.acme.digol.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CustomerMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;
    public CustomerResource toResource(Customer model) {
        return mapper.map(model, CustomerResource.class);
    }

    public Page<CustomerResource> modelListPage(List<Customer> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CustomerResource.class), pageable, modelList.size());
    }

    public Customer toModel(CreateCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    public Customer toModel(UpdateCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }


}
