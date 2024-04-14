package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Designation;
import com.AnkitIndia.jwtauthentication.responseDTO.DesignationDTO;

public interface DesignationService {

	public Designation save(Designation designation);
	public List<Designation> findAll();
	
	public Designation findOne(long id);
	
	public DesignationDTO getDesigNameToCode(String code);
	
	public List<DesignationDTO> getDesignation();
	
	public List<Map<String,Object>> designationListNew();
	
	public Designation update(Designation designation,long id);
	
	public Designation deleteDesignation(Designation designation,long id);
	
	public List<Designation> findDesignation(String searchtext);
}
