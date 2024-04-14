package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_cost_centre_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_cost_centreRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_cost_centre_masterDTO;

public interface Acc_cost_centre_masterService {
	public Acc_cost_centre_master save(Acc_cost_centre_master costcenter);
	
	
	public List<Acc_cost_centre_master> findAll();
	
	public Acc_cost_centre_master findOne(long id);
	
	public Acc_cost_centre_master update(Acc_cost_centre_master costcenter,long id);
	
	public List<Acc_cost_centre_masterDTO> getAccCostCentreN();

}
