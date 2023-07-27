package com.infosys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="shippers")
public class Shipper {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name="shipper_id")
	private Integer shipperId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column
	private String phone;
}
