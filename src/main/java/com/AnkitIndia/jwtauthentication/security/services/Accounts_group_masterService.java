package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Accounts_group_master;
import com.AnkitIndia.jwtauthentication.model.Designation;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Accounts_group_masterService {
	
	
	public SequenceIdDTO getSeqNoForAccGrp(String fin_year,String company);
	
	public List<Map<String,Object>> accGroupList(String company,String catagory);
	
	public List<Map<String,Object>> accountGroupList();
	
	public List<Map<String,Object>> accountParentGroupList();
	
	public List<Map<String,Object>> accountTypeList();
	
	public List<Map<String,Object>> accountCatagoryList();
	
	public Accounts_group_master save(Accounts_group_master accounts_group_master);
	
	public Accounts_group_master findOne(long id);
	
	public Accounts_group_master update(Accounts_group_master accounts_group_master,long id);
	
	public StatusDTO checkAccGroupUsage(String code);
	
	public Accounts_group_master deleteAccGrpMaster(Accounts_group_master Accounts_group_master,long id);
	
	public Accounts_group_master postingaccountsgroup(long id);

}
