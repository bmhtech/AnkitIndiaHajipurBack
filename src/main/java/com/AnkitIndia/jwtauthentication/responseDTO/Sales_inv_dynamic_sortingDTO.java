package com.AnkitIndia.jwtauthentication.responseDTO;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales_inv_dynamic_sortingDTO {
	
	private Long id;
	
	private String dynamic;
	
	private String backend;

	private String sortNumber;
	
	private String reportname;
}
