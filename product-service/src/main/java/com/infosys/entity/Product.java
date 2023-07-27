package com.infosys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="supplier_id")
	private Integer supplierId;
	
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	
	@Column(name="unit_price")
	private Double unitPrice;
	
	@Column(name="units_in_stock")
	private Integer unitsInStock;
	
	@Column(name="units_on_order")
	private Integer unitsOnOrder;
	
	@Column(name="reorder_level")
	private Integer reorderLevel;
	
	@Column
	private Integer discontinued;
}
