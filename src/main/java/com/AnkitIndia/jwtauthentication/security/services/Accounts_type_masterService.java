package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Accounts_type_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Accounts_type_masterService 
{
	public SequenceIdDTO getATSequenceId(String fin_year);
	
	public List<Map<String, Object>> getAccountsTypeList();
	
	public Accounts_type_master saveAccountsType(Accounts_type_master accounts_type_master);
	
	public Accounts_type_master findOne(long id);
	
	public Accounts_type_master deleteaccountstype(Accounts_type_master accounts_type_master,long id);
	
	public StatusDTO checkAccTypeUsage(String code);
}
