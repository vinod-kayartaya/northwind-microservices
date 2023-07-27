package com.infosys.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.customerservice.entity.Customer;
import com.infosys.customerservice.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repo;
	
	@GetMapping("/{id}")
	public Customer handleGetOne(@PathVariable String id) {
		return repo.findById(id).get();
	}
}
