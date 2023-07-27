package com.infosys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.entity.Category;
import com.infosys.model.CategoryDTO;
import com.infosys.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	private static CategoryDTO mapToCategoryDTO(Category category) {
		return CategoryDTO.builder()
				.categoryId(category.getCategoryId())
				.categoryName(category.getCategoryName())
				.description(category.getDescription())
				.build();
	}
	
	private static Category mapToCategory(CategoryDTO categoryDTO) {
		return Category.builder()
				.categoryId(categoryDTO.getCategoryId())
				.categoryName(categoryDTO.getCategoryName())
				.description(categoryDTO.getDescription())
				.build();
	}
	
	public CategoryDTO getCategoryById(Integer categoryId) {
		Optional<Category> result = repo.findById(categoryId);
		
		return result.isEmpty() ? null : mapToCategoryDTO(result.get());
	}
	
	public List<CategoryDTO> getAllCategories(){
		return repo.findAll()
					.stream()
					.map(CategoryService::mapToCategoryDTO)
					.toList();
	}
	
	// can be used for INSERT or UPDATE
	public void saveCategory(CategoryDTO categoryDTO) throws ServiceException {
		
		if(categoryDTO.getCategoryId()==null ||
				categoryDTO.getCategoryId() <=0 ) {
			throw new ServiceException("Category id is missing or invalid");
		}
		
		if(categoryDTO.getCategoryName()==null ||
				categoryDTO.getCategoryName().isBlank()) {
			throw new ServiceException("Category name is missing or blank");
		}
		
		repo.save(mapToCategory(categoryDTO));
		
	}
	
	// return the picture content for the given categoryId
	public byte[] getPicture(Integer categoryId) {
		Optional<Category> result = repo.findById(categoryId);
		if(result.isEmpty()) {
			return null;
		}
		return result.get().getPicture();
	}

	public void updatePicture(Integer categoryId, byte[] bytes) {
		Optional<Category> result = repo.findById(categoryId);
		if(result.isEmpty()) {
			throw new ServiceException("No data found for id " + categoryId);
		}
		
		Category c1 = result.get();
		c1.setPicture(bytes);
		repo.save(c1);
	}

	
}
















