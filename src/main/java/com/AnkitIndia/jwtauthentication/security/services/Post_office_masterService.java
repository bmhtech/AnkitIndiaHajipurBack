package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Post_office_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Post_office_masterDTO;

public interface Post_office_masterService {
	
	public Post_office_master save(Post_office_master post_office);
	
	public List<Post_office_master> findAll();

	public Post_office_master findOne(long id);
	
	public List<Post_office_masterDTO> getPostOfficeThruDist(String distid);
	
	public Post_office_masterDTO getPincodeThruPO(String po);
	
	public Post_office_master update(Post_office_master post_office,long id);
	
	public Post_office_master deletePostOffice(Post_office_master post_office,long id);
	
	public List<Post_office_master> findPostOffice(String searchtext);
	
	public List<Post_office_masterDTO> findPOThruPincode(String pincode,String dist);

}
