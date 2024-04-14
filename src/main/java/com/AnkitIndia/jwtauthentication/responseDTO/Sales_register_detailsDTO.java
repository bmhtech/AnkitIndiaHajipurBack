package com.AnkitIndia.jwtauthentication.responseDTO;
import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sales_register_detailsDTO {
	
	private Long id;
	private String reportfields;
    private String sales_report;
    private String reportname;
	private String static_report;
	

	
	
	
	
}
