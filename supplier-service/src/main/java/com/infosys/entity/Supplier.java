package com.infosys.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="suppliers")
public class Supplier {

	@Id
	@Column(name="supplier_id")
	private Integer supplierId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="contact_name")
	private String contactName;
	
	@Column(name="contact_title")
	private String contactTitle;
	
	@Column
	private String address;
	
	@Column
	private String city;
	
	@Column
	private String region;
	
	@Column(name="postal_Code")
	private String postalCode;
	
	@Column
	private String country;
	
	@Column
	private String phone;
	
	@Column
	private String fax;
	
	@Column(name="home_page")
	private String homepage;
}
