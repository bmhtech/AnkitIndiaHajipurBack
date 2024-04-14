package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import com.AnkitIndia.jwtauthentication.model.Misc_master;
import com.AnkitIndia.jwtauthentication.responseDTO.MiscMasterDTO;

public interface MiscMasterService {
	
	public Misc_master save(Misc_master misc);
	
	public List<Misc_master> findAll();
	
	public List<Misc_master> getMiscNCList();
	
	public Misc_master findOne(long id);
	
	public Misc_master update(Misc_master misc,long id);
	
	public List<MiscMasterDTO> getMiscNameCode();
	
	public List<MiscMasterDTO> getMiscList();
	
	public Misc_master deleteMisc_master(Misc_master misc,long id);
	
	public List<Misc_master> findMisc_master(String searchtext);
	
}

