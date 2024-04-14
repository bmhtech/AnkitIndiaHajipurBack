package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Broker_group;
import com.AnkitIndia.jwtauthentication.responseDTO.Broker_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Broker_groupService {
		
	public SequenceIdDTO getBgrpSequenceId(String prefix,String company);
	
	public Broker_group save(Broker_group broker_group);
	
	public List<Broker_group> findAll(String company);
	
	public Broker_group findOne(long id);
	
	public List<Broker_groupDTO> getBrokerGroupList(String company);
	

	public List<Broker_groupDTO> getBrokerGroupList();

	
	public Broker_groupDTO getBroParentGroup(String code, String company);
	
	public Broker_group update(Broker_group broker_group,long id); 
	
	public Broker_group deleteBrokerGrp(Broker_group broker_group,long id);
	
	public MessageStatusDTO chkBrokerGroupStatus(String grpname, String company);
	
	public List<Broker_group> findBrokerGrp(String searchtext,String company);
	
	public StatusDTO chkBrokerGrpCodeStatus(String code, String company);
}
