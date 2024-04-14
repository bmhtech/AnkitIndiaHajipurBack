package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;


import com.AnkitIndia.jwtauthentication.model.Acc_sub_group_master;

public interface Acc_sub_group_masterService {

	
	public Acc_sub_group_master save(Acc_sub_group_master subgroup);
        
	public List<Acc_sub_group_master> findAll();
	
	public Acc_sub_group_master findOne(long id);
	
	
	public Acc_sub_group_master update(Acc_sub_group_master subgroup,long id);
	
}
