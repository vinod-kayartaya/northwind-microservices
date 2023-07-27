package com.infosys.orderservice.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order_details")
public class LineItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="order_id")
	private Integer orderId;
	
	@Id
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="unit_price")
	private Double unitPrice;
	
	@Column
	private Integer quantity;
	
	@Column
	private Double discount;
	
}
