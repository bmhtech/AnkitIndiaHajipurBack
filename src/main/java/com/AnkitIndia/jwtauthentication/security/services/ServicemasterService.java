package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Servicemaster;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface ServicemasterService {

	public SequenceIdDTO getSSequenceId(String fin_year);
	
	public Servicemaster save(Servicemaster servicemaster);
	
	public List<Map<String, Object>> getServiceMasterList(String fin_year);
	
	public Servicemaster findOne(long id);
	
	public List<Map<String, Object>> serviceRetriveList(String s_no);
	
	public Servicemaster update(Servicemaster servicemaster,long id);
	
	public Servicemaster delete(Servicemaster servicemaster, long id);
	
	public StatusDTO checkServiceMasterUsage(String service_no);
	
}
