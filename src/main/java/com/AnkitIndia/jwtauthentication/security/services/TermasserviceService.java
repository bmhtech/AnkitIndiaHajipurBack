package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Termasservice;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface TermasserviceService {

	public SequenceIdDTO getTSequenceId(String fin_year);
	
	public Termasservice save(Termasservice termasservice);
	
	public List<Map<String, Object>> getTermasServiceList(String fin_year);
	
	public Termasservice findOne(long id);
	
	public Termasservice update(Termasservice termasservice, long id);
	
	public Termasservice delete(Termasservice termasservice, long id);
	
	public List<Map<String, Object>> getTermAsService();
	
}
