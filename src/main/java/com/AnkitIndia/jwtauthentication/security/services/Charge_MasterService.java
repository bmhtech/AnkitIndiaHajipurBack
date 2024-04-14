package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Charge_Master;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Charge_MasterdtlsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;


public interface Charge_MasterService {
	
	
	public Charge_Master save (Charge_Master charge_master);
	
	public List<Charge_Master> findAll();
	
	public Charge_Master findOne(long id);
	
	public List<Charge_MasterdtlsDTO> chargeRetriveList(String c_id);
	
	public List<Charge_MasterDTO> getChargeMasterList();
	
	public List<Charge_MasterdtlsDTO> getChargeMasterdtlsList(String code);
	
	public Charge_Master update(Charge_Master pMaster,long id);
	
	public Charge_Master deleteCharge(Charge_Master charge_master,long id);
	
	public List<Charge_Master> findChargeMaster(String searchtext);
	
	public StatusDTO checkChargeMasterUsage(String code);
}
