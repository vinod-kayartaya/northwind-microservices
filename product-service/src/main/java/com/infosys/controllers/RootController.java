package com.infosys.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping(path="/", produces = "text/plain")
	public String handleGet() {
		return "Access product service at /api/products";
	}
}
