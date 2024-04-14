package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Area_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Area_masterDTO;

public interface Area_masterService {
	public Area_master save(Area_master area_master);
	
	public List<Area_master> findAll();
	
	public Area_master findOne(long id);
	
	public List<Area_masterDTO> areaMasterList();
	
	public Area_master update(Area_master pMaster,long id);
	
	public Area_master deleteAreaMaster(Area_master pMaster,long id);
	
	public List<Area_master> findAreas(String searchtext);
}
