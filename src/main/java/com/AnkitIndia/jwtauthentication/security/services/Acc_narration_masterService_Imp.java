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

@Service
public class Acc_narration_masterService_Imp implements Acc_narration_masterService {
	
	@Autowired
	Acc_narrationRepository acc_narrationRepository;
	
	@Transactional
	public Acc_narration_master save(Acc_narration_master narration)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_narrationRepository.countId() != null ) {
			slno=Long.parseLong(acc_narrationRepository.countId());
		}
		String prefix="ANC";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		narration.setNarration_Id(gen_sno);
		narration.setModified_type("INSERTED");
		narration.setInserted_by("xxxx");
		narration.setInserted_on(ldt);
		narration.setUpdated_by("NA");
		narration.setUpdated_on(ldt);
		narration.setDeleted_by("NA");
		narration.setDeleted_on(ldt);
		
		return acc_narrationRepository.save(narration);
	}
	
	public List<Acc_narration_master> findAll()
	{
		return acc_narrationRepository.findAll();
	}
	
	public Acc_narration_master findOne(long id)
	{
		Optional<Acc_narration_master> op = this.acc_narrationRepository.findById(id);
		return op.get();
	}
	
	public Acc_narration_master update(Acc_narration_master narration,long id)
	{
		Optional<Acc_narration_master> op = acc_narrationRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		narration.setModified_type("UPDATED");
		narration.setInserted_by("xxxx");
		narration.setInserted_on(ldt);
		narration.setUpdated_by("NA");
		narration.setUpdated_on(ldt);
		narration.setDeleted_by("NA");
		narration.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			narration.setId(id);
		}
		return acc_narrationRepository.save(narration);
	}

}
