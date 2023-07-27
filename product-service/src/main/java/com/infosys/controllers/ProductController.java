package com.infosys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.model.CustomResponse;
import com.infosys.model.ProductDTO;
import com.infosys.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(path="/by-price-range", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> handleGetByPriceRange(
			@RequestParam(defaultValue = "0") Double min, 
			@RequestParam(defaultValue = "99999") Double max){
		
		return ResponseEntity.ok(service.getByPriceRange(min, max));
	}
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> handleGetAll(
			@RequestParam(name="_page", defaultValue = "1") Integer pageNum, 
			@RequestParam(name="_limit", defaultValue = "10")Integer pageSize){
		
		List<ProductDTO> list = service.getAllProducts(pageNum, pageSize);
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping(path="/{productId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> hanldeGetOne(@PathVariable Integer productId){
		ProductDTO productDTO = service.getProductById(productId);
		if(productDTO==null) {
			CustomResponse cr = new CustomResponse("No data found for product id " + productId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cr);
		}
		
		return ResponseEntity.ok(productDTO);
	}
	
	@GetMapping(path="/search", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> handleGetSearch(
			@RequestParam String fieldName, 
			@RequestParam String fieldValue){
		
		return ResponseEntity.ok(service.searchByField(fieldName, fieldValue));
	}

}
