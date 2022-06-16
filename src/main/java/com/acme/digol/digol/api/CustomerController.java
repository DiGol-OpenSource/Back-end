package com.acme.digol.digol.api;
import com.acme.digol.digol.domain.service.CustomerService;
import com.acme.digol.digol.mapping.CustomerMapper;
import com.acme.digol.digol.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customers")
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper mapper;

    public CustomerController(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get customers", description = "Get All customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportFieldResource.class))})
    })
    @GetMapping
    public Page<CustomerResource> getAllCustomers(Pageable pageable) {
        return mapper.modelListPage(customerService.getAll(), pageable);
    }
    @Operation(summary = "Get customers with id ", description = "Get  customers with id.")
    @GetMapping("{customerId}")
    public CustomerResource getCustomerById(@PathVariable Long customerId) {
        return mapper.toResource(customerService.getById(customerId));
    }
    @Operation(summary = "Post customers with id ", description = "Post  customers.")
    @PostMapping
    public CustomerResource createCustomer(@RequestBody CreateCustomerResource resource) {
        return mapper.toResource(customerService.create(mapper.toModel(resource)));
    }
    @Operation(summary = "Edit customers ", description = "Edit customers.")
    @PutMapping("{customerId}")
    public CustomerResource updateCustomer(@PathVariable Long customerId, @RequestBody UpdateCustomerResource resource) {
        return mapper.toResource(customerService.update(customerId, mapper.toModel(resource)));
    }
    @Operation(summary = "Delete customers ", description = "Delete customers.")
    @DeleteMapping("{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        return customerService.delete(customerId);
    }
}
