package com.AnkitIndia.jwtauthentication.transResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Broker_master_addDTO {
	
	private Long id;
	
	private String broker_Id;
	
	private String broker_code;
	
	private String fin_year;
	
	private String company_id;
	
	private String website;
	
	private String country;
	
	private String state_code;
	
	private String state;
	
	private String dist_code;
	
	private String dist;
	
	private String city_code;
	
	private String city;
	
	private String postid;
	
	private String post_office;
	
	private String pin;
	
	private String address1;
	
	private String address2;
	
	private String address3;

}
