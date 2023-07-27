package com.infosys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infosys.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findAllByUnitPrice(Double unitPrice); // from Product where unitPrice=?1

	public List<Product> findAllByUnitsInStock(Integer unitsInStock); // from Product where unitsInStock=?1

	public List<Product> findAllByUnitsOnOrder(Integer unitsOnOrder);

	public List<Product> findAllByReorderLevel(Integer reorderLevel);

	public List<Product> findAllByDiscontinued(Integer discontinued);

	@Query("from Product where lower(quantityPerUnit) like lower(concat('%', ?1, '%'))")
	public List<Product> findAllByQuantityPerUnit(String match);
	

	@Query("from Product where lower(productName) like lower(concat('%', ?1, '%'))")
	public List<Product> findAllByProductName(String match);

	public List<Product> findAllByUnitPriceBetween(Double min, Double max); // from Product where unitPrice between ?1 and ?2

}
