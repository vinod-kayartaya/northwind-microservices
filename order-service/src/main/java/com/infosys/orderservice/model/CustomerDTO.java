package com.infosys.orderservice.model;

import lombok.Data;

@Data
public class CustomerDTO {
	private String customerId;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String city;
}
