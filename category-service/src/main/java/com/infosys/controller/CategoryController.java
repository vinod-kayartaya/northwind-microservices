package com.infosys.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.model.CategoryDTO;
import com.infosys.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> handleGetAll() {
		return ResponseEntity.ok(service.getAllCategories());
	}

	@GetMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> handleGetOneById(@PathVariable Integer categoryId) {
		CategoryDTO c1 = service.getCategoryById(categoryId);

		if (c1 == null) {
			Map<String, Object> err = new LinkedHashMap<>();
			err.put("message", "No data found for id " + categoryId);
			err.put("timestamp", new Date());
			return ResponseEntity.status(404).body(err);
		}

		return ResponseEntity.ok(c1);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public void handlePostOne(@RequestBody CategoryDTO categoryDTO) {

		Integer id = categoryDTO.getCategoryId();

		if (id != null) {
			CategoryDTO c1 = service.getCategoryById(id);
			if (c1 != null) {
				// category already exists!
				throw new RuntimeException("Category with id " + id + " already exists!");
			}
		}

		service.saveCategory(categoryDTO);
	}

	@PutMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> handlePutOne(@PathVariable Integer categoryId,
			@RequestBody CategoryDTO categoryDTO) {
		categoryDTO.setCategoryId(categoryId); // replace the id in the payload with the path-variable
		service.saveCategory(categoryDTO);
		return ResponseEntity.ok(categoryDTO);
	}

	@GetMapping(path = "/{categoryId}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> handleGetPicture(@PathVariable Integer categoryId) {
		byte[] picture = service.getPicture(categoryId);
		return picture ==null ?
				ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
				ResponseEntity.ok(picture);
	}

	@PutMapping(path = "/{categoryId}/picture", consumes = MediaType.IMAGE_JPEG_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public void handlePutPicture(@PathVariable Integer categoryId, @RequestBody byte[] picture) {
		service.updatePicture(categoryId, picture);
	}

}







