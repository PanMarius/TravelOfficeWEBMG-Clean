package com.inqoo.travelofficeweb.controller;

import com.inqoo.travelofficeweb.model.Customer;
import com.inqoo.travelofficeweb.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(path = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> customers(@RequestParam(name = "firstNameFragment", required = false) String firstNameFragment,
                                                    @RequestParam(name = "addressFragment", required = false) String addressFragment,
                                                    @RequestParam(name = "trip", required = false) Boolean trip) {
        return ResponseEntity.ok(customerService.getAllCustomers(firstNameFragment, addressFragment, trip));
    }

    @GetMapping(path = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> customerById(@PathVariable("customerId") Integer id) {
        return ResponseEntity.ok(
                customerService.getAllCustomers(null, null, null)
                        .get(id));
    }

    @PostMapping(path = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewTrip(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        URI savedCustomerId = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(savedCustomerId).build();
    }
}