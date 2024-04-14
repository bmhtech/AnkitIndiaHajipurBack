package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.HubMaster;

public interface GodownMasterService {
	
	public List<GodownMaster> findAll();
	
	public GodownMaster save(GodownMaster godownMaster);
	
	public GodownMaster update(GodownMaster godownMaster,long id);
	
	public GodownMaster deleteGodownMaster(GodownMaster godownMaster,long id);
	
	public List<GodownMaster> findGodownMaster(String searchtext);
	
	public GodownMaster findOne(long id);
	
	public List<GodownMaster> getGodownMasterList(String company);
}
