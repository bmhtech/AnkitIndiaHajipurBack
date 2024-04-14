package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Acc_sub_group_master;
import com.AnkitIndia.jwtauthentication.repository.Acc_sub_groupRepository;

@Service
public class Acc_sub_group_masterService_Imp implements Acc_sub_group_masterService{
	@Autowired
	Acc_sub_groupRepository acc_sub_groupRepository;
	
	@Transactional
	public Acc_sub_group_master save(Acc_sub_group_master subgroup)
	{
        LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		if(acc_sub_groupRepository.countId() != null ) {
			slno=Long.parseLong(acc_sub_groupRepository.countId());
		}
		String prefix="ASG";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		subgroup.setSubgroup_Id(gen_sno);
		subgroup.setModified_type("INSERTED");
		subgroup.setInserted_by("xxxx");
		subgroup.setInserted_on(ldt);
		subgroup.setUpdated_by("NA");
		subgroup.setUpdated_on(ldt);
		subgroup.setDeleted_by("NA");
		subgroup.setDeleted_on(ldt);
		
		return acc_sub_groupRepository.save(subgroup);
	}
	public List<Acc_sub_group_master> findAll()
	{
		return acc_sub_groupRepository.findAll();
	}
	
	public Acc_sub_group_master findOne(long id)
	{
		Optional<Acc_sub_group_master> op = this.acc_sub_groupRepository.findById(id);
		return op.get();
	}
	
	public Acc_sub_group_master update(Acc_sub_group_master subgroup,long id)
	{
		Optional<Acc_sub_group_master> op = acc_sub_groupRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		subgroup.setModified_type("UPDATED");
		subgroup.setInserted_by("xxxx");
		subgroup.setInserted_on(ldt);
		subgroup.setUpdated_by("NA");
		subgroup.setUpdated_on(ldt);
		subgroup.setDeleted_by("NA");
		subgroup.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			subgroup.setId(id);
		}
		return acc_sub_groupRepository.save(subgroup);
	}

}
