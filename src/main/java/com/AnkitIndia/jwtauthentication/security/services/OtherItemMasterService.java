package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.OtherItemMaster;

public interface OtherItemMasterService {

	public OtherItemMaster save(OtherItemMaster otherItemMaster);
	
	public OtherItemMaster update(OtherItemMaster otherItemMaster,long id);
	
	public OtherItemMaster delete(OtherItemMaster otherItemMaster,long id);
	
	public List<OtherItemMaster> findOtherItemMaster(String searchtext);
	
	public OtherItemMaster findOne(long id);
	
	public List<Map<String,Object>> getOtherItemList(String company);
	
	public List<Map<String,Object>> getOtherItemMasterList(String company);
	
}
