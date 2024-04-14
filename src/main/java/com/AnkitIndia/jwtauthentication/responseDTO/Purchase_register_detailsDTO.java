package com.AnkitIndia.jwtauthentication.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase_register_detailsDTO {
	private Long id;
	private String purchase_report;
	private String reportfields;
	private String reportname;
}
