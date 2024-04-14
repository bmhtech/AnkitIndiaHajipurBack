package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Purpose_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Purpose_masterDTO;

public interface Purpose_masterService {
	
	public Purpose_master save(Purpose_master purpose_master);
	
	public Purpose_master update(Purpose_master purpose_master,long id);
	
	public List<Purpose_master> findAll();
	
	public Purpose_master findOne(long id);
	
	public List<Purpose_masterDTO> getPurposeList();
	
	public Purpose_master deletePurpose(Purpose_master pMaster,long id);
	
	public List<Purpose_master> findPurpose(String searchtext);

}
