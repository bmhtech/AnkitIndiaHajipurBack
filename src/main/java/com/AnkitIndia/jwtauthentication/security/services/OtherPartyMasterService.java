package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.OtherPartyMaster;

public interface OtherPartyMasterService {
	
	
	public OtherPartyMaster save(OtherPartyMaster otherPartyMaster);
	
	public OtherPartyMaster update(OtherPartyMaster otherPartyMaster,long id);
	
	public OtherPartyMaster delete(OtherPartyMaster otherPartyMaster,long id);
	
	public List<OtherPartyMaster> findOtherPartyMaster(String searchtext);
	
	public OtherPartyMaster findOne(long id);
	
	public List<Map<String,Object>> getOtherPartyList(String company);
	
	public List<Map<String,Object>> getOtherPartyMasterList(String company);
	
}
