package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.JobWorkItemAllocation;
import com.AnkitIndia.jwtauthentication.repository.Cust_bussiness_partnerRepository;
import com.AnkitIndia.jwtauthentication.repository.CustomUomMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.Item_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.JobWorkItemAllocationRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class JobWorkItemAllocationService_Imp  implements JobWorkItemAllocationService
{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Item_masterRepository item_masterRepository;
	
	@Autowired
	Cust_bussiness_partnerRepository cust_bussiness_partnerRepository;
	
	@Autowired
	CustomUomMasterRepository customUomMasterRepository;
	
	@Autowired
	JobWorkItemAllocationRepository jobWorkItemAllocationRepository;
	
	@Transactional
	public JobWorkItemAllocation save(JobWorkItemAllocation jobWorkItemAllocation) 
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=jobWorkItemAllocationRepository.countJobWorkItem();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueId6("JIA",slno);
			jobWorkItemAllocation.setJob_item_alloc_id(gen_sno);
			
			if(Utility.isNullOrEmpty(jobWorkItemAllocation.getJob_item())) 
			{
				jobWorkItemAllocation.setJob_item_name(item_masterRepository.itemNameById(jobWorkItemAllocation.getJob_item()).getItem_name());
			}
			else
			{
				jobWorkItemAllocation.setJob_item_name("None");
			};
			
			if(Utility.isNullOrEmpty(jobWorkItemAllocation.getParty())) 
			{
				jobWorkItemAllocation.setParty_name(cust_bussiness_partnerRepository.getCustomer(jobWorkItemAllocation.getParty()).getCp_name());
			}
			else 
			{
				jobWorkItemAllocation.setParty_name("None");
			}
			
			if(Utility.isNullOrEmpty(jobWorkItemAllocation.getQty_uom())) 
			{
				jobWorkItemAllocation.setQty_uom_name(customUomMasterRepository.getCustomUomById(jobWorkItemAllocation.getQty_uom()).getDescription());
			}
			else 
			{
				jobWorkItemAllocation.setQty_uom_name("None");
			}
			
			jobWorkItemAllocation.setModified_type("INSERTED");
			jobWorkItemAllocation.setInserted_by(userRepository.getUserDetails(jobWorkItemAllocation.getUsername()).getName());
			jobWorkItemAllocation.setInserted_on(ldt);
			jobWorkItemAllocation.setUpdated_by("NA");
			jobWorkItemAllocation.setUpdated_on(ldt);
			jobWorkItemAllocation.setDeleted_by("NA");
			jobWorkItemAllocation.setDeleted_on(ldt);
			
		return jobWorkItemAllocationRepository.save(jobWorkItemAllocation);
		
	}
	
	public List<Map<String, Object>> JobWorkItemAllocationList()
	{
		List<Map<String, Object>> JobWorkItemAllocationList = new ArrayList<Map<String, Object>>();
		JobWorkItemAllocationList.addAll(jobWorkItemAllocationRepository.JobWorkItemAllocationList());
		return JobWorkItemAllocationList;
	}
	
	public JobWorkItemAllocation findOne(long id)
	{
		Optional<JobWorkItemAllocation> op=this.jobWorkItemAllocationRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public JobWorkItemAllocation update(JobWorkItemAllocation jobWorkItemAllocation,long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<JobWorkItemAllocation> op = jobWorkItemAllocationRepository.findById(id);
		
		jobWorkItemAllocation.setJob_item_alloc_id(op.get().getJob_item_alloc_id());
		
		if(Utility.isNullOrEmpty(jobWorkItemAllocation.getJob_item())) 
		{
			jobWorkItemAllocation.setJob_item_name(item_masterRepository.itemNameById(jobWorkItemAllocation.getJob_item()).getItem_name());
		}
		else
		{
			jobWorkItemAllocation.setJob_item_name("None");
		};
		
		if(Utility.isNullOrEmpty(jobWorkItemAllocation.getParty())) 
		{
			jobWorkItemAllocation.setParty_name(cust_bussiness_partnerRepository.getCustomer(jobWorkItemAllocation.getParty()).getCp_name());
		}
		else 
		{
			jobWorkItemAllocation.setParty_name("None");
		}
		
		if(Utility.isNullOrEmpty(jobWorkItemAllocation.getQty_uom())) 
		{
			jobWorkItemAllocation.setQty_uom_name(customUomMasterRepository.getCustomUomById(jobWorkItemAllocation.getQty_uom()).getDescription());
		}
		else 
		{
			jobWorkItemAllocation.setQty_uom_name("None");
		}
		
		
		jobWorkItemAllocation.setModified_type("INSERTED");
		jobWorkItemAllocation.setInserted_by(op.get().getInserted_by());
		jobWorkItemAllocation.setInserted_on(op.get().getInserted_on());
		jobWorkItemAllocation.setUpdated_by(userRepository.getUserDetails(jobWorkItemAllocation.getUsername()).getName());
		jobWorkItemAllocation.setUpdated_on(ldt);
		jobWorkItemAllocation.setDeleted_by(op.get().getDeleted_by());
		jobWorkItemAllocation.setDeleted_on(op.get().getDeleted_on());
		
		if(op.isPresent())
		{
			jobWorkItemAllocation.setId(id);
		}
		return jobWorkItemAllocationRepository.save(jobWorkItemAllocation);
	}
	
	@Transactional
	public JobWorkItemAllocation delete(JobWorkItemAllocation jobWorkItemAllocation, long id)
	{
		LocalDateTime ldt = LocalDateTime.now();
		Optional<JobWorkItemAllocation> op = jobWorkItemAllocationRepository.findById(id);
		
		JobWorkItemAllocation jia=op.get();
		
		jia.setModified_type("DELETED");
		jia.setInserted_by(op.get().getInserted_by());
		jia.setInserted_on(op.get().getInserted_on());
		jia.setUpdated_by(op.get().getUpdated_by());
		jia.setUpdated_on(op.get().getUpdated_on());
		jia.setDeleted_by(userRepository.getUserDetails(jobWorkItemAllocation.getUsername()).getName());
		jia.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			jia.setId(id);
		}
		
		return jobWorkItemAllocationRepository.save(jia);
	}
	
	public Map<String, Object> getItemQtythruLoading(String item,String party)
	{
		return jobWorkItemAllocationRepository.getItemQtythruLoading(item, party);
	}
	
	public Map<String, Object> getJwAllocationRestWt(String party)
	{
		return jobWorkItemAllocationRepository.getJwAllocationRestWt(party);
	}

}
