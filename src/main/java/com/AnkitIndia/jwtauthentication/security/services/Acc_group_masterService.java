package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Acc_group_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_group_masterRepository;
import com.AnkitIndia.generators.UniqueID;

public interface Acc_group_masterService {
	
	public Acc_group_master save(Acc_group_master agm);
	
	public List<Acc_group_master> findAll();
    
	public Acc_group_master findOne(long id);
	
	public Acc_group_master update(Acc_group_master agm,long id);
	
	/*
	public List<Acc_group_master> findByName() {
		return acc_group_masterRepository.getGroupNameList();
	}*/
	
    public List<Acc_group_master> getGroupcode();
	 
	public List<Acc_group_master> getGroupNameByCode(String grpCode);
}
