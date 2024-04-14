package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.HubMaster;

public interface HubMasterService {
	
	public List<HubMaster> findAll();
	
	public HubMaster save(HubMaster hubMaster);
	
	public HubMaster update(HubMaster hubMaster,long id);
	
	public HubMaster deleteHubMaster(HubMaster hubMaster,long id);
	
	public List<HubMaster> findHubMaster(String searchtext);
	
	public HubMaster findOne(long id);
	
	public List<HubMaster> getHubMasterList(String company);

}
