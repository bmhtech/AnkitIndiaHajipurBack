package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Groupmaster;
import com.AnkitIndia.jwtauthentication.responseDTO.GroupmasterDTO;

public interface GroupmasterService {
	/*public Groupmaster save(Groupmaster groupmaster);
	public List<Groupmaster> findAll();*/
	public List<GroupmasterDTO> getGroupmasterCList();
	
	public GroupmasterDTO getGroupmasterNByCList(String code);
	
}
