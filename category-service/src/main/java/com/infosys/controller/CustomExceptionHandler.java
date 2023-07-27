package com.infosys.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infosys.service.ServiceException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> handleServiceException(ServiceException e) {

		Map<String, Object> err = new LinkedHashMap<>();
		err.put("message", e.getMessage());
		err.put("timestatmp", new Date());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception e) {

		Map<String, Object> err = new LinkedHashMap<>();
		err.put("message", e.getMessage());
		err.put("timestatmp", new Date());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);

	}

}
