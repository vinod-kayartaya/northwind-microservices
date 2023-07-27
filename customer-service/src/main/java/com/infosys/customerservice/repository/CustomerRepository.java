package com.infosys.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.customerservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
