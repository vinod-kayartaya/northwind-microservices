package com.infosys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@XmlRootElement(name="product")
public class ProductDTO {
	private Integer productId;
	private String productName;
	
	private CategoryDTO category;
	private SupplierDTO supplier;
	
	private String quantityPerUnit;
	private Double unitPrice;
	private Integer unitsInStock;
	private Integer unitsOnOrder;
	private Integer reorderLevel;
	private Integer discontinued;
}
