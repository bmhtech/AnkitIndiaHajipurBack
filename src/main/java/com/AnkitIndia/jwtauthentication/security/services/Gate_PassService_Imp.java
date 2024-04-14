package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Gate_Pass;
import com.AnkitIndia.jwtauthentication.model.Gate_Pass_Item_Dtls;
import com.AnkitIndia.jwtauthentication.repository.Gate_PassRepository;

@Service
@Repository
public class Gate_PassService_Imp implements Gate_PassService {
	
	@Autowired
	Gate_PassRepository gate_PassRepository;
	
	@Transactional
	public Gate_Pass saveGatePass(Gate_Pass gate_Pass)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(gate_PassRepository.countId() != null ) {
			slno=Long.parseLong(gate_PassRepository.countId());
		}
		String prefix="GP";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		gate_Pass.setGatepass_id(gen_sno);
		
		gate_Pass.setCompany_id("xxxxx");
		gate_Pass.setFin_year("2019-2020");
		gate_Pass.setModified_type("INSERTED");
		gate_Pass.setInserted_by("xxxx");
		gate_Pass.setInserted_on(ldt);
		gate_Pass.setUpdated_by("NA");
		gate_Pass.setUpdated_on(ldt);
		gate_Pass.setDeleted_by("NA");
		gate_Pass.setDeleted_on(ldt);
		
		//Dynamic
		Set<Gate_Pass_Item_Dtls> itemSet = new HashSet<Gate_Pass_Item_Dtls>();
		itemSet.addAll(gate_Pass.getGate_Pass_Item_Dtls());
		for(Gate_Pass_Item_Dtls itemDts : itemSet)
		{
			itemDts.setGatepass_id(gen_sno);
			itemDts.setFin_year("2019-2020");
			itemDts.setModified_type("INSERTED");
			itemDts.setInserted_by("xxxx");
			itemDts.setInserted_on(ldt);
			itemDts.setUpdated_by("NA");
			itemDts.setUpdated_on(ldt);
			itemDts.setDeleted_by("NA");
			itemDts.setDeleted_on(ldt);
		
		}
		gate_Pass.setGate_Pass_Item_Dtls(itemSet);
		
		return gate_PassRepository.save(gate_Pass);
		
	}
	
	public List<Gate_Pass> findAllGPass(){
		return gate_PassRepository.findAll();
	}

}
