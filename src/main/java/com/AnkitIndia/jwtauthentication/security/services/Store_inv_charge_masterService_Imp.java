package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Store_inv_charge_master;
import com.AnkitIndia.jwtauthentication.model.Store_inv_charge_master_dtls;
import com.AnkitIndia.jwtauthentication.repository.LedgermasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Store_inv_charge_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;
@Service
public class Store_inv_charge_masterService_Imp implements Store_inv_charge_masterService {
	
	@Autowired
	Store_inv_charge_masterRepository store_inv_charge_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LedgermasterRepository ledgermasterRepository;
	
	@Transactional
	public Store_inv_charge_master save(Store_inv_charge_master store_inv_charge_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(store_inv_charge_masterRepository.countId() != null ) {
			slno=Long.parseLong(store_inv_charge_masterRepository.countId());
		}
		String prefix="SIC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		store_inv_charge_master.setStore_charge_id(gen_sno);
		store_inv_charge_master.setCompany_id(store_inv_charge_master.getCompany_id());
		store_inv_charge_master.setFin_year(store_inv_charge_master.getFin_year());
		store_inv_charge_master.setModified_type("INSERTED");
		store_inv_charge_master.setInserted_on(ldt);
		store_inv_charge_master.setInserted_by(userRepository.getUserDetails(store_inv_charge_master.getUsername()).getName());
		store_inv_charge_master.setUpdated_by(store_inv_charge_master.getUpdated_by());
		store_inv_charge_master.setUpdated_on(ldt);
		store_inv_charge_master.setDeleted_by("NA");
		store_inv_charge_master.setDeleted_on(ldt);
		
		
		Set<Store_inv_charge_master_dtls> store_inv_charge_master_dtls=new HashSet<Store_inv_charge_master_dtls>();
		store_inv_charge_master_dtls.addAll(store_inv_charge_master.getStore_inv_charge_master_dtls());
		for(Store_inv_charge_master_dtls sDetails:store_inv_charge_master_dtls) 
		{
			sDetails.setStore_charge_id(gen_sno);
			if(Utility.isNullOrEmpty(sDetails.getStore_charge_ac()))
			{
				sDetails.setStore_charge_ac_name(ledgermasterRepository.getLedgers(sDetails.getStore_charge_ac()).getLedgername());
			}
			else 
			{
				sDetails.setStore_charge_ac_name("");
			}
			sDetails.setCompany_id(store_inv_charge_master.getCompany_id());
			sDetails.setFin_year(store_inv_charge_master.getFin_year());
			sDetails.setModified_type("INSERTED");
			sDetails.setInserted_by(store_inv_charge_master.getInserted_by());
			sDetails.setInserted_on(ldt);
			sDetails.setUpdated_by("NA");
			sDetails.setUpdated_on(ldt);
			sDetails.setDeleted_by("NA");
			sDetails.setDeleted_on(ldt);
			sDetails.setUsername(store_inv_charge_master.getUsername());
		}
		store_inv_charge_master.setStore_inv_charge_master_dtls(store_inv_charge_master_dtls);
		
		return store_inv_charge_masterRepository.save(store_inv_charge_master);	
	}
	
	public List<Map<String, Object>> getStoreChargesList()
	{
		return store_inv_charge_masterRepository.getStoreChargesList();
	}
	
	public List<Map<String, Object>> getStoreChargeMasterDtlsList(String storeid)
	{
		return store_inv_charge_masterRepository.getStoreChargeMasterDtlsList(storeid);
	}
	
	public Store_inv_charge_master findOne(long id)
	{
		Optional<Store_inv_charge_master> op=this.store_inv_charge_masterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Store_inv_charge_master update(Store_inv_charge_master store_inv_charge,long id)  
	{
		Optional<Store_inv_charge_master> op =store_inv_charge_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		store_inv_charge.setStore_charge_id(op.get().getStore_charge_id());
		store_inv_charge.setCompany_id(store_inv_charge.getCompany_id());
		store_inv_charge.setFin_year(store_inv_charge.getFin_year());
		store_inv_charge.setModified_type("INSERTED");
		store_inv_charge.setInserted_on(op.get().getInserted_on());
		store_inv_charge.setInserted_by(op.get().getInserted_by());
		store_inv_charge.setUpdated_by(userRepository.getUserDetails(store_inv_charge.getUsername()).getName());
		store_inv_charge.setUpdated_on(ldt);
		store_inv_charge.setDeleted_by("NA");
		store_inv_charge.setDeleted_on(ldt);
		
		String mstatus="UPDATED";
		//Update Party
		store_inv_charge_masterRepository.updateStoreInvChgsDetails(op.get().getStore_charge_id(),mstatus);
		
		Set<Store_inv_charge_master_dtls> store_inv_charge_master_dtls=new HashSet<Store_inv_charge_master_dtls>();
		store_inv_charge_master_dtls.addAll(store_inv_charge.getStore_inv_charge_master_dtls());
		for(Store_inv_charge_master_dtls storedetails:store_inv_charge_master_dtls) 
		{
			storedetails.setStore_charge_id(op.get().getStore_charge_id());
			if(Utility.isNullOrEmpty(storedetails.getStore_charge_ac()))
			{
				storedetails.setStore_charge_ac_name(ledgermasterRepository.getLedgers(storedetails.getStore_charge_ac()).getLedgername());
			}
			else 
			{
				storedetails.setStore_charge_ac_name("");
			}
			storedetails.setCompany_id(store_inv_charge.getCompany_id());
			storedetails.setFin_year(store_inv_charge.getFin_year());
			storedetails.setModified_type("INSERTED");
			storedetails.setInserted_by(userRepository.getUserDetails(store_inv_charge.getUsername()).getName());
			storedetails.setInserted_on(ldt);
			storedetails.setUpdated_by(store_inv_charge.getUpdated_by());
			storedetails.setUpdated_on(store_inv_charge.getUpdated_on());
			storedetails.setDeleted_by("NA");
			storedetails.setDeleted_on(ldt);
			storedetails.setUsername(store_inv_charge.getUsername());
		}
		store_inv_charge.setStore_inv_charge_master_dtls(store_inv_charge_master_dtls);
		
		return store_inv_charge_masterRepository.save(store_inv_charge);
	}
	
	public StatusDTO checkStoreChargeMasterUsage(String code)
 	{
		StatusDTO result = new StatusDTO();
		
		boolean purchase=false;
		
		if(store_inv_charge_masterRepository.checkStoreChargeMasterUsage(code))
		{
			purchase=true;
		}
		
		if(purchase == true)
		{
			result.setStatus("Yes");
		}
		else
		{
			result.setStatus("No");
		}
		
		return result;
	 }
	
	@Transactional
	public Store_inv_charge_master delete(Store_inv_charge_master storeinventorymaster,long id) 
	{
		Optional<Store_inv_charge_master> op = store_inv_charge_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		String mstatus="DELETED";
		Store_inv_charge_master sim=op.get();
		
		sim.setModified_type("DELETED");
		sim.setInserted_by(op.get().getInserted_by());
		sim.setInserted_on(op.get().getInserted_on());
		sim.setUpdated_by(op.get().getUpdated_by());
		sim.setUpdated_on(op.get().getUpdated_on());
		sim.setDeleted_by(userRepository.getUserDetails(sim.getUsername()).getName());
		sim.setDeleted_on(ldt);
		sim.setUsername(sim.getUsername());
		
		store_inv_charge_masterRepository.updateStoreInvChgsDetails(op.get().getStore_charge_id(),mstatus);
		
		if(op.isPresent())
		{
			sim.setId(id);
		}
		return store_inv_charge_masterRepository.save(sim);
	}
	
	
	
}
