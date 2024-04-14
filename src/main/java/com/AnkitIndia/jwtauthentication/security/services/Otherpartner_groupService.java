package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.model.Otherpartner_group;
import com.AnkitIndia.jwtauthentication.responseDTO.Otherpartner_groupDTO;

public interface Otherpartner_groupService {
	public Otherpartner_group save(Otherpartner_group otherpartner_group);
	
	public List<Otherpartner_group> findAll();
	
	public Otherpartner_group findOne(long id);
	
	public List<Otherpartner_groupDTO> getOtherpartnerGroupList();
}
