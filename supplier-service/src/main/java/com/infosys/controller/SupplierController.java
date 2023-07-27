package com.infosys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.model.SupplierDTO;
import com.infosys.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierService service;
	
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> handleGetOne(@PathVariable("id") Integer supplierId){
		
		SupplierDTO supplier = service.getSupplierById(supplierId);
		if(supplier==null) {
			Map<String, Object> err = new HashMap<>();
			err.put("message", "No data found for id " + supplierId);
			err.put("timestamp", new Date());
			return ResponseEntity.status(404).body(err);
		}
		
		return ResponseEntity.ok(supplier);
	}

}
