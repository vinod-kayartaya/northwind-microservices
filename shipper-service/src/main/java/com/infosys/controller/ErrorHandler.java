package com.infosys.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.dto.CustomResponseRecord;

@ControllerAdvice
@RestController
public class ErrorHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponseRecord> handleAnyException(Exception e){
		CustomResponseRecord err = new CustomResponseRecord(e.getMessage());
		return ResponseEntity.status(500).body(err);
	}
}
