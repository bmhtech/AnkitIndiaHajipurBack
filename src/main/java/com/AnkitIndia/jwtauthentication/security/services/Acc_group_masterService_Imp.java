package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_group_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_group_masterRepository;

@Service
public class Acc_group_masterService_Imp implements Acc_group_masterService {
	@Autowired
	Acc_group_masterRepository acc_group_masterRepository;

	@Transactional
	public Acc_group_master save(Acc_group_master agm) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		long slno=0;
		if(acc_group_masterRepository.countId() != null ) {
			slno=Long.parseLong(acc_group_masterRepository.countId());
		}
		String prefix="AGM";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		//agm.setBusinessunit_name(acc_group_masterRepository.getBCodeToBUnitName(agm.getBusinessunit_code()));
		System.out.println("Get Name:-------"+agm.getBusinessunit_name());
		
		agm.setGroup_Id(gen_sno);
		agm.setModified_type("INSERTED");
		agm.setInserted_by("Saheb");
		agm.setInserted_on(ldt);
		agm.setUpdated_by("NA");
		agm.setUpdated_on(ldt);
		agm.setDeleted_by("NA");
		agm.setDeleted_on(ldt);
		 
		return acc_group_masterRepository.save(agm);
	}
	
	public List<Acc_group_master> findAll()
	{
		return acc_group_masterRepository.findAll();
	}
    
	public Acc_group_master findOne(long id)
	{
		Optional<Acc_group_master> op = this.acc_group_masterRepository.findById(id);
		return op.get();
	}
	
	public Acc_group_master update(Acc_group_master agm,long id)
	{
		Optional<Acc_group_master> op = acc_group_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		agm.setModified_type("UPDATED");
		agm.setInserted_by("xxxx");
		agm.setInserted_on(ldt);
		agm.setUpdated_by("NA");
		agm.setUpdated_on(ldt);
		agm.setDeleted_by("NA");
		agm.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			agm.setId(id);
		}
		return acc_group_masterRepository.save(agm);
	}
	
	/*
	public List<Acc_group_master> findByName() {
		return acc_group_masterRepository.getGroupNameList();
	}*/

	  public List<Acc_group_master> getGroupcode(){
			return acc_group_masterRepository.groupCode();
		}
		
	    public List<Acc_group_master> getGroupNameByCode(String grpCode){
			return acc_group_masterRepository.groupNameByCode(grpCode);
		}
	    
}
