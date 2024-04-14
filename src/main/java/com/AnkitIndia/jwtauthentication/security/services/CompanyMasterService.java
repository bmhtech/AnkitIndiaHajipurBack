package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Company_master;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Company_licence_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface CompanyMasterService {
	public Company_master save(Company_master company); 
	
	public List<Company_master> findAll();
	
	public List<Company_master> getCompanyNCCode();
	
	public Company_master findOne(long id);
	
	public List<Company_licence_detailsDTO> compLiceRetriveList(String cp_id);
	
	public Company_master update(Company_master company,long id);
	
	public Company_master deleteCompany(Company_master company,long id);
	
	public List<CompanyMasterDTO> getCompanyNameCode();
	
	public SequenceIdDTO getCompanySequenceId(String prefix,String company);
	
	public List<Company_master> findCompanys(String searchtext);
	
	public Company_master getCompanyDetails(String compid);
	
		
	
}
