package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Zone_master;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Zone_masterService {
	
	public Zone_master save(Zone_master zone_master);
	
	public List<Zone_master> findAll();
	
	public Zone_master findOne(long id);
	
	public Zone_master deleteZone(Zone_master zone_master,long id);
	
	public Zone_master updateZone(Zone_master zone_master,long id);
	
	public List<Zone_master> findZone(String searchtext);
	
	public StatusDTO checkZoneMasterUsage(String code);

}
