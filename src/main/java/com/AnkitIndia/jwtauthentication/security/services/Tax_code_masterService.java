package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Tax_code_details;
import com.AnkitIndia.jwtauthentication.model.Tax_code_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Tax_code_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Tax_code_detailsDTO;

public interface Tax_code_masterService {
	
	public Tax_code_master save(Tax_code_master tax_code_master);
	
	public Tax_code_master update(Tax_code_master tax_code_master,long id);
	
	public List<Tax_code_master> findAll();
	
	public Tax_code_master findOne(long id);
	
	public List<Tax_code_detailsDTO> taxCodeDtlsRetriveList(String t_id);
	
	public Tax_code_detailsDTO getTaxCodeDetails(String t_id);
	
	 
	public Tax_code_detailsDTO getTaxCodeDetailsname(String tax_name);
	
	public List<Tax_code_masterDTO> getTaxNameCode(String company);
	//Delete
	public List<Tax_code_masterDTO> getTaxNameCode();
	
	public List<Tax_code_master> getTaxCode();
	
	public Tax_code_masterDTO getTaxNameByTaxCode(String code);
	
	public List<Tax_code_detailsDTO> getTaxNameRate();
	
	public Tax_code_details taxlistbycode(String code);
	
	public SequenceIdDTO getTaxCodeSequenceId(String prefix,String company);
	
	public List<Tax_code_master> findTaxCode(String searchtext);
	
	public Tax_code_master deleteTaxCode(Tax_code_master tax_code_master,long id);
	
	public Map<String,Object> getServiceItemTax(String name);

}
