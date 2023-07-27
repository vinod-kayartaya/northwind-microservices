package com.infosys.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@XmlRootElement(name="response")
public class CustomResponse {
	private String message;
	private Date timestatmp = new Date();

	public CustomResponse(String message) {
		this.message = message;
	}

}
