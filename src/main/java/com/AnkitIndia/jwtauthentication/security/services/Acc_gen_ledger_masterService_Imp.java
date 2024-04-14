package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_gen_ledger_master;
import com.AnkitIndia.jwtauthentication.model.VehicleTypeMaster;
import com.AnkitIndia.jwtauthentication.repository.Acc_gen_ledgerRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_gen_ledger_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.VehicleTypeMasterDTO;

@Service
public class Acc_gen_ledger_masterService_Imp implements Acc_gen_ledger_masterService{
	
	@Autowired
	Acc_gen_ledgerRepository acc_gen_ledgerRepository;
	
	@Transactional
	public Acc_gen_ledger_master save(Acc_gen_ledger_master genledger)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_gen_ledgerRepository.countId() != null ) {
			slno=Long.parseLong(acc_gen_ledgerRepository.countId());
		}
		String prefix="AGL";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		genledger.setGaledger_Id(gen_sno);
		genledger.setModified_type("INSERTED");
		genledger.setInserted_by("xxxx");
		genledger.setInserted_on(ldt);
		genledger.setUpdated_by("NA");
		genledger.setUpdated_on(ldt);
		genledger.setDeleted_by("NA");
		genledger.setDeleted_on(ldt);
		
		return acc_gen_ledgerRepository.save(genledger);
	}
	
	public List<Acc_gen_ledger_master> findAll()
	{
		return acc_gen_ledgerRepository.findAll();
	}
	
	public Acc_gen_ledger_master findOne(long id) 
	{
		Optional<Acc_gen_ledger_master> op =this.acc_gen_ledgerRepository.findById(id);
		return op.get();
	}
	
	public Acc_gen_ledger_master update(Acc_gen_ledger_master genledger,long id)
	{
		Optional<Acc_gen_ledger_master> op = acc_gen_ledgerRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		genledger.setModified_type("UPDATED");
		genledger.setInserted_by("xxxx");
		genledger.setInserted_on(ldt);
		genledger.setUpdated_by("NA");
		genledger.setUpdated_on(ldt);
		genledger.setDeleted_by("NA");
		genledger.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			genledger.setId(id);
		}
		return acc_gen_ledgerRepository.save(genledger);
	}

	public List<Acc_gen_ledger_masterDTO> getAccLedgerList()
	{
		List<Acc_gen_ledger_master> modelList=acc_gen_ledgerRepository.getAccLedgerList(true);
		
		Type listType = new TypeToken<List<Acc_gen_ledger_masterDTO>>() {}.getType();
		
		List<Acc_gen_ledger_masterDTO> acLedger = new ModelMapper().map(modelList,listType);
		
		return acLedger;
		
	}
}
