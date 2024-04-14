package com.AnkitIndia.jwtauthentication.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tds_masterDTO {
	
	private String tds_id;
	
	private String tds_type;
	
	private String tds_section;
	
	private double tds_rate;
	
	private String tds_acc;
	
	private String tds_accname;
	
}
