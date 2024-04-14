package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Tds_master;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tds_masterDTO;

public interface Tds_masterService 
	{
	
		public Tds_master save(Tds_master tds_master);
		
		public List<Tds_master> findAll();
		
		public Tds_master findOne(long id);
		
		public List<Tds_masterDTO> getTdsMNCList();
		
		public Tds_masterDTO getTDSRate(String tdsid);
		
		public Tds_master update(Tds_master pMaster,long id);
		
		public Tds_master deleteTds(Tds_master tds_master,long id);
		
		public List<Tds_master> findTds(String searchtext,String company);
		
		public StatusDTO checkTdsMasterUsage(String code);
		
		public Tds_master gettdsdetails(String taxcode);

		/* public SequenceIdDTO getTdsSequenceId(String prefix,String company); */
		
	}