package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Country_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Country_masterDTO;

public interface Country_masterService {
	public Country_master save(Country_master country_master);
	
	public List<Country_master> findAll();
	
	public List<Country_masterDTO> countrylist();
	
	public Country_master update(Country_master country,long id);
	
	public Country_master deleteCompany(Country_master country,long id);
	
}
