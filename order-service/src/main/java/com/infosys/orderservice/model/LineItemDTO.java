package com.infosys.orderservice.model;

import lombok.Data;

@Data
public class LineItemDTO {
	private ProductRecord product;
	private Double unitPrice;
	private Integer quantity;
	private Double discount;
}
