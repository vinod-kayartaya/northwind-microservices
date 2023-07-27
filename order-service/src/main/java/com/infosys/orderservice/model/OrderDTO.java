package com.infosys.orderservice.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private Integer orderId;

	private Date orderDate;

	private Date requiredDate;

	private Date shippedDate;

	private CustomerDTO customer;

	private ShipperRecord shipper;

	private Double freight;
	
	private List<LineItemDTO> lineItems;
}
