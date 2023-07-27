package com.infosys.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.orderservice.model.CustomResponseRecord;
import com.infosys.orderservice.model.OrderDTO;
import com.infosys.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	
	@GetMapping(path="/{orderId}")
	public ResponseEntity<?> handleGetOne(@PathVariable Integer orderId){
		OrderDTO order = service.getOrderById(orderId);
		if(order==null) {
			CustomResponseRecord resp = new CustomResponseRecord("No data found for id " + orderId);
			return ResponseEntity.status(404).body(resp);
		}
		
		return ResponseEntity.ok(order);
	}
}
