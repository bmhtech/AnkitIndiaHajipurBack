package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Wm_charges_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Wm_charges_masterDTO;

public interface Wm_charges_masterService {
	
	public Wm_charges_master save(Wm_charges_master wm_charges_master);
	
	public List<Wm_charges_master> findAll();
	
	public Wm_charges_masterDTO getWeighmentChargeMasters(String wm_charge_id);
	
	public List<Wm_charges_masterDTO> getWeighmentCharges();
	
	public Wm_charges_masterDTO getWeighmentChargeThruVtype(String vehicletype);
	
	public Wm_charges_master findOne(Long id);
	
	public Wm_charges_master update(Wm_charges_master pMaster,long id);
	
	public SequenceIdDTO getWnChargesSequenceId(String prefix,String company);
	
	public List<Wm_charges_master> findWmCharges(String searchtext);
	
	public Wm_charges_master deleteWmCharges(Wm_charges_master wm_charges_master,long id);
	
	public StatusDTO checkWmChgsMasterUsage(String code);

}
