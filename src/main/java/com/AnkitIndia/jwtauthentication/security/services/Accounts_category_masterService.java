package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Accounts_category_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Accounts_category_masterService {
	
	public List<Map<String, Object>> getAccountsCategoryList();
	
	public SequenceIdDTO getAccountCatagorySequenceId(String fin_year);
	
	public Accounts_category_master save(Accounts_category_master accounts_category_master);
	
	public Accounts_category_master findOne(long id);
	
	public Accounts_category_master update(Accounts_category_master accounts_category_master,long id);
	
	public StatusDTO checkAccCatagoryUsage(String code);
	
	public Accounts_category_master deleteAccCatagoryMaster(Accounts_category_master accounts_category_master,long id);

}
