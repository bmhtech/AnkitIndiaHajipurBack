package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Bom_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_input_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_listDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_masterDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_output_detailsDTO;
import com.AnkitIndia.jwtauthentication.transResponseDTO.Bom_resource_costDTO;

public interface Bom_masterService {
	
	public SequenceIdDTO getBMSequenceId(String prefix,String bunit,String sfloor,String company);
	
	public Bom_master save(Bom_master bom_master);

	public List<Bom_master> findAllBom();
	
	public List<Bom_listDTO> findAllBomList();
	
	public Bom_master findOne(long id);
	
	public List<Bom_masterDTO> getBoms();
	
	public List<Bom_masterDTO> getBomThruProcess(String bunit,String sfloor,String process,String company);
	
	public Bom_masterDTO getBomDetails(String bomid,String company);
	
	public Bom_master update(Bom_master bom_master,long id);
	
	public List<Bom_input_detailsDTO> getBomInputDetails(String prodid);
	
	public List<Map<String,Object>> getBomInputDetailsNew(String prodid);
	
	public Bom_input_detailsDTO getBomInputDtls(String prodid,String itemid,String packingid);
	
	public List<Bom_output_detailsDTO> getBomOutputDetails(String prodid);
	
	//public Bom_output_detailsDTO getBomOutputDtls(String prodid,String itemid);
	public Bom_output_detailsDTO getBomOutputDtls(String prodid,String itemid,String packingid);

	public List<Bom_resource_costDTO> getBomResourceCost(String prodid);
	
	public Bom_master deleteBomMaster(Bom_master bommaster,long id);
	
}
