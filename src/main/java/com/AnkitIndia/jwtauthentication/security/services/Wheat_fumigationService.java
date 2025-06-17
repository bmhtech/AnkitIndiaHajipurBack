package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Wheat_fumigation;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Wheat_fumigationService {

	public List<Map<String,Object>> getWheatFumigationList(String finyear);
	
	public SequenceIdDTO getFumigationSequenceId(String company);
	
	public Wheat_fumigation save(Wheat_fumigation wf);
	
	public Wheat_fumigation findOne(long id);
	
	public List<Map<String,Object>> getWheatFumigationDetails(String fumigation_id);
	
	public Wheat_fumigation update(Wheat_fumigation wf,long id);
	
	public Wheat_fumigation delete(Wheat_fumigation wf,long id);
	
	public List<Map<String,Object>> getAllWheatFumiDtlsList(String type);

	public StatusDTO updateWheatFumiDetails(long id,String fumigation_id,String allocate_date,String company,String finyear,String user,
			String action,String allocate,String pcmw_sign_name,String supervisor_sign_name,String lab_sign_name,String manpower,String degassing_date,String degassing_time,String wheat_fumi_qc);
	
}