package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Acc_cost_category_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_category_masterDTO;

public interface Acc_cost_category_masterService {
	
	public Acc_cost_category_master save(Acc_cost_category_master costcatagory);
	
	public List<Acc_cost_category_master> findAll();
	
	public Acc_cost_category_master findOne(long id);
	
	public Acc_cost_category_master update(Acc_cost_category_master costcatagory,long id);
	
	public List<Acc_cost_category_masterDTO > getAccCostCategoriCNList();
	
	public Acc_cost_category_masterDTO getAccCostCatNListbyC(String grpCode);
	
}
