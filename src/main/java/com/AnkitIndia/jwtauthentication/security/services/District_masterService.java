package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.District_master;

import com.AnkitIndia.jwtauthentication.responseDTO.District_masterDTO;

public interface District_masterService {
	public District_master save(District_master district_master);
	
	public List<District_master> findAll();
	
	public District_master findOne(long id);
	
	public List<District_masterDTO> districtlist();
	
	public List<District_masterDTO> districtListByState(String state_Name);
	
	public List<District_masterDTO> getDistrictThruState(String stateid);
	
	public District_master update(District_master district_master,long id);
	
	public District_master deleteDistrict(District_master district_master,long id);
	
	public List<District_master> findDistricts(String searchtext);
}
