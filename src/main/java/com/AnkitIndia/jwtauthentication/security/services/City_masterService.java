package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.City_master;
import com.AnkitIndia.jwtauthentication.model.Designation;
import com.AnkitIndia.jwtauthentication.responseDTO.City_masterDTO;


public interface City_masterService {
	
	public City_master save(City_master city_master);
	
	public List<City_master> findAll();

	public City_master findOne(long id);
	
	public List<City_masterDTO> citylist();
	
	public List<City_masterDTO> citylistByDistrict(String dist_name);
	
	public List<City_masterDTO> getCityListThruDistrict(String distid);
	
	public City_master update(City_master city_master,long id);
	
	public City_master deleteCity(City_master city_master,long id);
	
	public List<City_master> findCities(String searchtext);

}
