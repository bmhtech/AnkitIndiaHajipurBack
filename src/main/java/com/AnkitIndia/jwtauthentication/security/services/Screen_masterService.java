package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Screen_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Screen_masterDTO;

public interface Screen_masterService 
	{
	
		public Screen_master save(Screen_master screen_master);
		
		public Screen_master update(Screen_master screen_master,long id);
		
		public List<Screen_master> findAll();
		
		public Screen_master findOne(long id);
		
		public List<Screen_masterDTO> getScreenMNCList();
		
		public Screen_master deleteScreen(Screen_master screen_master,long id);
		
		public List<Screen_master> findScreen(String searchtext);
		
	}