package com.AnkitIndia.jwtauthentication.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supp_bussiness_partner_addressDTO {
	
	private Long id;
	
	private String bp_Id;
	
	private String company_id;
	
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
	
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	private String updated_by;
	
	private String address;

}
