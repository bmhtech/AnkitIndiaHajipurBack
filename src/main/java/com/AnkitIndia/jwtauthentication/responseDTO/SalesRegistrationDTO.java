package com.AnkitIndia.jwtauthentication.responseDTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class SalesRegistrationDTO {
    private Long id;
	
	private String sales_name;

	public SalesRegistrationDTO(Long id, String sales_name) {
		super();
		this.id = id;
		this.sales_name = sales_name;
	}
	
	


	
}
