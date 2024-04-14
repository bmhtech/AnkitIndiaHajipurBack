package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_gen_ledger_master;
import com.AnkitIndia.jwtauthentication.model.Acc_group_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_gen_ledgerRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_gen_ledger_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleTypeMasterDTO;

public interface Acc_gen_ledger_masterService {

	public Acc_gen_ledger_master save(Acc_gen_ledger_master genledger);
	
	public List<Acc_gen_ledger_master> findAll();
	
	public Acc_gen_ledger_master findOne(long id);
	
	public Acc_gen_ledger_master update(Acc_gen_ledger_master genledger,long id);
	
	public List<Acc_gen_ledger_masterDTO> getAccLedgerList();
	
	//public List<Acc_gen_ledger_master> getLedgercode();
	 
	//public List<Acc_gen_ledger_master> getLedgerNameByCode(String ledgerCode);

	
}
