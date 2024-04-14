package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.AnkitIndia.jwtauthentication.model.Custom_uom_master;
import com.AnkitIndia.jwtauthentication.responseDTO.CustomUomMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

@Service

public interface CustomUomMasterService {
	
	public Custom_uom_master save(Custom_uom_master custom);
	
	public List<Custom_uom_master> findAll();
	
	public List<CustomUomMasterDTO> getCustomUOMNCList();
	
	public List<CustomUomMasterDTO> getStandardCustomUOM(String company);
	//Delete
	public List<CustomUomMasterDTO> getStandardCustomUOM();
	
	public List<CustomUomMasterDTO> getWeighmentCustomUOM();
	
	public Custom_uom_master findOne(long id);
	
	public Custom_uom_master update(Custom_uom_master custom,long id);
	
	public CustomUomMasterDTO getUomByIGroup(String code);
	
	public List<Map<String,Object>> getUomList();
	
	public List<CustomUomMasterDTO> getCustomUOMs(String uomtype);
	
	public Custom_uom_master getCustomUomsbyname(String uomname);
	
	
	public CustomUomMasterDTO getCustomUomById(String cuomid);
	
	public SequenceIdDTO getCustomUOMSequenceId(String prefix,String company);
	
	public Custom_uom_master deleteCustom_uom(Custom_uom_master custom,long id);
	
	public List<Custom_uom_master> findCustom_uom(String searchtext);
	
	public Custom_uom_master exportuom(long id);
}
