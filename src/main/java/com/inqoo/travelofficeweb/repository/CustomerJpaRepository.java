package com.inqoo.travelofficeweb.repository;

import com.inqoo.travelofficeweb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerJpaRepository extends JpaRepository<Customer, Integer>,
        JpaSpecificationExecutor<Customer> {

}
