package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_pay_mode_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_pay_modeRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Acc_pay_mode_masterDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class Acc_pay_mode_masterService_Imp implements Acc_pay_mode_masterService{
	@Autowired
	Acc_pay_modeRepository acc_pay_modeRepository;
	
	@Transactional
	public Acc_pay_mode_master save(Acc_pay_mode_master paymode)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_pay_modeRepository.countId() != null ) {
			slno=Long.parseLong(acc_pay_modeRepository.countId());
		}
		String prefix="APM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		paymode.setPaymode_id(gen_sno);
		paymode.setModified_type("INSERTED");
		paymode.setInserted_by("xxxx");
		paymode.setInserted_on(ldt);
		paymode.setUpdated_by("NA");
		paymode.setUpdated_on(ldt);
		paymode.setDeleted_by("NA");
		paymode.setDeleted_on(ldt);
		paymode.setCompany_id("bisu");
		
		return acc_pay_modeRepository.save(paymode);
	}
	
	public List<Acc_pay_mode_master> findAll()
	{
		return acc_pay_modeRepository.findAll();
	}
	
	public Acc_pay_mode_master findOne(long id)
	{
		Optional<Acc_pay_mode_master> op = this.acc_pay_modeRepository.findById(id);
		return op.get();
	}
	
	public Acc_pay_mode_master update(Acc_pay_mode_master paymode,long id)
	{
		Optional<Acc_pay_mode_master> op = acc_pay_modeRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		paymode.setModified_type("UPDATED");
		paymode.setInserted_by("xxxx");
		paymode.setInserted_on(ldt);
		paymode.setUpdated_by("NA");
		paymode.setUpdated_on(ldt);
		paymode.setDeleted_by("NA");
		paymode.setDeleted_on(ldt);
		paymode.setCompany_id("bisu");
		
		if(op.isPresent())
		{
			paymode.setId(id);
		}
		return acc_pay_modeRepository.save(paymode);
	}
	
	public List<Acc_pay_mode_masterDTO> getAccPaymodeNc() {
		List<Acc_pay_mode_master> modelList=acc_pay_modeRepository.getAccPaymodeNc(true);
		
		// Create Conversion Type
		Type listType = new TypeToken<List<Acc_pay_mode_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<Acc_pay_mode_masterDTO> paymodeList = new ModelMapper().map(modelList,listType);
		
		return paymodeList;	
	}
}
