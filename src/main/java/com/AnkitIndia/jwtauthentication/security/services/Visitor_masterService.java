package com.AnkitIndia.jwtauthentication.security.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.AnkitIndia.jwtauthentication.model.Visitor_master;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Visitor_masterService {

	public Visitor_master save(Visitor_master visitor_master,MultipartFile files) throws IOException;
	
	public List<Visitor_master> findAll();
	
	public Visitor_master findOne(long id);
	
	public Visitor_master update(Visitor_master visitor_master,MultipartFile files) throws IOException;
	
	public StatusDTO checkVisitorMasterDeletion(String visitor_id);
	
	public Visitor_master deleteVisitorMaster(Visitor_master visitor_master,long id);
	
}
