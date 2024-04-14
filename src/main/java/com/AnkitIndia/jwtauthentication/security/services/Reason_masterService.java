package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Reason_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Reason_masterDTO;

public interface Reason_masterService 
	{
	
		public Reason_master save(Reason_master reason_master);
		
		public List<Reason_master> findAll();
		
		public Reason_master findOne(long id);
		
		public List<Reason_masterDTO> getReasonMNCList();
		
		public List<Reason_masterDTO> getReasonIndent();
		
		public List<Reason_masterDTO> getPurTermReasons();
		
		public Reason_master update(Reason_master reason_master,long id);
		
		public Reason_master deleteReason(Reason_master reason_master,long id);
		
		public List<Reason_master> findReason(String searchtext);
		
	}