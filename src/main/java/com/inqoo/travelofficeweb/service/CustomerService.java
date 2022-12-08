package com.inqoo.travelofficeweb.service;

import com.inqoo.travelofficeweb.model.Customer;
import com.inqoo.travelofficeweb.model.CustomerNameDetails;
import com.inqoo.travelofficeweb.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerJpaRepository customerJpaRepository;

    public void saveCustomer(Customer customer) {
        customerJpaRepository.save(customer);
    } // logikę biznesową

    public List<Customer> getAllCustomers(String firstNameFragment, String addressFragment, Boolean withoutAnyTrip) {
        CustomerNameDetails details = CustomerNameDetails.builder().firstName(firstNameFragment).build();
        Customer exampleCustomer = Customer
                .builder()
                .customerNameDetails(details)
                .build();
        ExampleMatcher firstLastNameFragmentMatcher = ExampleMatcher
                .matchingAll()
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("Firstname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        List<Customer> result = customerJpaRepository.findAll(Example.of(exampleCustomer, firstLastNameFragmentMatcher));
        return withoutAnyTrip != null && withoutAnyTrip
                ? result.stream()
                .filter(e -> e.getTrips() == null || e.getTrips().isEmpty())
                .collect(Collectors.toList())
                : result.stream()
                .filter(e -> e.getTrips() != null && !e.getTrips().isEmpty())
                .collect(Collectors.toList());
    }

}



