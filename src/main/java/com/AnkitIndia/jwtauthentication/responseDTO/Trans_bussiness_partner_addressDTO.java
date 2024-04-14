package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trans_bussiness_partner_addressDTO {
	private String website;
	
	private String country;
	
	private String state_code;
	
	private String state;
	
	private String dist_code;
	
	private String district;
	
	private String city_code;
	
	private String city;
	
	private String postid;
	
	private String post_office;
	
	private Long pincode;
	
	private String add1;
	
	private String add2;
	
	private String add3;
	
	private String fin_year;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;

	
}
