package com.infosys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.CustomResponseRecord;
import com.infosys.dto.ShipperRecord;
import com.infosys.service.ShipperService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/shippers")
//@CrossOrigin(origins = {"http://localhost:5500", "http://localhost:3000"})
@CrossOrigin
@Slf4j
public class ShipperController {

	@Autowired
	private ShipperService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ShipperRecord> handleGetAll() {
		return service.getAllShippers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShipperRecord handlePost(@RequestBody ShipperRecord shipperRecord) {
		return service.addNewShipper(shipperRecord);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> handleGetOne(@PathVariable Integer id) {
		log.info("sending data for shipper id {}", id);
		ShipperRecord shipperRecord = service.getShipperById(id);

		if (shipperRecord == null) {
			CustomResponseRecord err = new CustomResponseRecord("No data found for id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		}

		return ResponseEntity.ok(shipperRecord);
	}

}
