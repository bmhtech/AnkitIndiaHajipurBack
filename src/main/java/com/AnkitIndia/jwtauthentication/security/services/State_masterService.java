package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.State_master;
import com.AnkitIndia.jwtauthentication.responseDTO.State_masterDTO;

public interface State_masterService
{
	public State_master save(State_master state_master);
	
	public List<State_master> findAll();
	
	public List<State_masterDTO> statelist();
	
	public List<State_masterDTO> statelistByCountry(String country_name);
	
	public State_master findOne(long id);
	
	public State_master update(State_master state_master,long id);
	
	public List<State_master> statelistByCountryUserprofile();
	
	public void importexcel(MultipartFile file);
}
