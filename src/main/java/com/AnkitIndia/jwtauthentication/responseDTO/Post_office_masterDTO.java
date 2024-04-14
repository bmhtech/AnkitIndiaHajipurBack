package com.AnkitIndia.jwtauthentication.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post_office_masterDTO 
{
	private String postid;
	
	private String post_office;
	
	private int pincode;
	
	private String dist_name;
	
	private String state_name;
	
	private String country_name;
}
