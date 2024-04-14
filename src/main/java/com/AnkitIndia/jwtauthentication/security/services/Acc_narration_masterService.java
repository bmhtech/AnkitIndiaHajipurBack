package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_narration_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_narrationRepository;


public interface Acc_narration_masterService {

	public Acc_narration_master save(Acc_narration_master narration);
	
	public List<Acc_narration_master> findAll();
	
	public Acc_narration_master findOne(long id);
	
	public Acc_narration_master update(Acc_narration_master narration,long id);
	
}
