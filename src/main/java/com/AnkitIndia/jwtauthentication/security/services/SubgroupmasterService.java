package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Subgroupmaster;
import com.AnkitIndia.jwtauthentication.responseDTO.SubgroupmasterDTO;

public interface SubgroupmasterService {
	public List<SubgroupmasterDTO> getSubgroupNames();
	public List<Subgroupmaster> getSubgroupByGr(String gr);
	public SubgroupmasterDTO getSubGroupmasterNByC(String code);
}
