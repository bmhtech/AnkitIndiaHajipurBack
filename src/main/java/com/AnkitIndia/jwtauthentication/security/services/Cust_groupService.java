package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Cust_group;
import com.AnkitIndia.jwtauthentication.responseDTO.Cust_groupDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Cust_groupService{
	
	public SequenceIdDTO getCgrpSequenceId(String prefix,String company);
	
	public Cust_group save(Cust_group cust_group);
	
	public List<Cust_group> findAll();
	
	public Cust_group findOne(long id);
	
	public List<Cust_group> getCustomerGroupList();
	
	public List<Cust_groupDTO> getCustGroupNCList();
	
	public Cust_groupDTO getCustParentGroup(String code);
	
	public Cust_groupDTO getCustomerControlAccounts(String custid);
	
	public Cust_group update(Cust_group cust_group,long id);
	
	public Cust_group deleteCustGrp(Cust_group cust_group,long id);
	
	public MessageStatusDTO chkCustGroupStatus(String grpname);
	
	public List<Cust_group> findCustomerGrp(String searchtext,String company);
	
	public StatusDTO chkCustGrpCodeStatus(String code);
	
}
