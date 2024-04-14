package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Jw_grn_itemtag;
import com.AnkitIndia.jwtauthentication.model.Store_inv_charge_master;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Store_inv_charge_masterService {

	public Store_inv_charge_master save (Store_inv_charge_master store_inv_charge_master);
	
	public List<Map<String, Object>> getStoreChargesList();
	
	public List<Map<String, Object>> getStoreChargeMasterDtlsList(String storeid);
	
	public Store_inv_charge_master findOne(long id);
	
	public Store_inv_charge_master update(Store_inv_charge_master store_inv_charge_master,long id);
	
	public StatusDTO checkStoreChargeMasterUsage(String code);
	
	public Store_inv_charge_master delete(Store_inv_charge_master store_inv_charge_master,long id);
	
}
