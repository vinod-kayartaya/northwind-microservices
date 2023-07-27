package com.infosys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.infosys.entity.Product;
import com.infosys.model.CategoryDTO;
import com.infosys.model.ProductDTO;
import com.infosys.model.SupplierDTO;
import com.infosys.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Value("${category.service-url}")
	private String categoryServiceUrl;
	
	@Value("${supplier.service-url}")
	private String supplierServiceUrl;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	private ProductDTO getProductDTO(Product product) {
		ProductDTO p1 = ProductDTO.builder()
				.productId(product.getProductId())
				.productName(product.getProductName())
				.quantityPerUnit(product.getQuantityPerUnit())
				.unitPrice(product.getUnitPrice())
				.unitsInStock(product.getUnitsInStock())
				.unitsOnOrder(product.getUnitsOnOrder())
				.reorderLevel(product.getReorderLevel())
				.discontinued(product.getDiscontinued()).build();
		
		
		// we need to go and get the category data from the category-service
		// and set that to the p1.category
		try {
			CategoryDTO c1 = webClientBuilder.build()
					.get()
					.uri(categoryServiceUrl + product.getCategoryId())
					.retrieve()
					.bodyToMono(CategoryDTO.class)
					.block();
			p1.setCategory(c1);
		} catch (Exception e) {
			log.warn("error", e);
		}
		
		// get the supplier information based on product.supplierId
		try {
			
			SupplierDTO s1 = webClientBuilder.build()
				.get()
				.uri(supplierServiceUrl+product.getSupplierId())
				.retrieve()
				.bodyToMono(SupplierDTO.class)
				.block();

			p1.setSupplier(s1);
		}
		catch(Exception e) {
			log.warn("error", e);
		}
		log.info("p1 is {}", p1);
		return p1;
	}

	public ProductDTO getProductById(Integer productId) {
		Optional<Product> result = repo.findById(productId);
		if (result.isEmpty()) {
			return null;
		}

		return getProductDTO(result.get());
	}

	public List<ProductDTO> getAllProducts(Integer pageNum, Integer pageSize) {

		PageRequest pr = PageRequest.of(pageNum - 1, pageSize);

		return repo.findAll(pr) // Page<Product>
				.stream() // Stream<Product>
				.map(this::getProductDTO) // Stream<ProductDTO>
				.toList(); // List<ProductDTO>
	}

	public List<ProductDTO> searchByField(String fieldName, String fieldValue) {
		List<Product> list = null;

		switch (fieldName) {
		case "unitPrice":
			list = repo.findAllByUnitPrice(Double.valueOf(fieldValue));
			break;
		case "unitsInStock":
			list = repo.findAllByUnitsInStock(Integer.valueOf(fieldValue));
			break;
		case "unitsOnOrder":
			list = repo.findAllByUnitsOnOrder(Integer.valueOf(fieldValue));
			break;
		case "reorderLevel":
			list = repo.findAllByReorderLevel(Integer.valueOf(fieldValue));
			break;
		case "discontinued":
			list = repo.findAllByDiscontinued(Integer.valueOf(fieldValue));
			break;
		case "quantityPerUnit":
			list = repo.findAllByQuantityPerUnit(fieldValue);
			break;
		case "productName":
			list = repo.findAllByProductName(fieldValue);
			break;
		}

		return list.stream().map(this::getProductDTO).toList();
	}

	public List<ProductDTO> getByPriceRange(Double min, Double max) {
		List<Product> list = repo.findAllByUnitPriceBetween(min, max);
		return list.stream().map(this::getProductDTO).toList();
	}
}











