package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.TaskAllocation;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.TaskAllocationRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
@Service
public class TaskAllocationService_Imp implements TaskAllocationService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskAllocationRepository taskAllocationRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	
	@Transactional
	public TaskAllocation save(TaskAllocation taskAllocation)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=taskAllocationRepository.countId();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String fin_yearspit[]=taskAllocation.getFin_year().split("-");
			String final_fyear=fin_yearspit[0].substring(fin_yearspit[0].length()-2, fin_yearspit[0].length())+fin_yearspit[1].substring(fin_yearspit[1].length()-2, fin_yearspit[1].length());
			
			String prefix="TA-"+final_fyear+"-";
			
			String gen_sno=UniqueIDTransaction.uniqueId6(prefix,slno);
			
			taskAllocation.setAllocation_id(gen_sno);
			taskAllocation.setModified_type("INSERTED");
			taskAllocation.setInserted_by(userRepository.getUserDetails(taskAllocation.getUsername()).getName());
			taskAllocation.setInserted_on(ldt);
			taskAllocation.setUpdated_by("NA");
			taskAllocation.setUpdated_on(ldt);
			taskAllocation.setDeleted_by("NA");
			taskAllocation.setDeleted_on(ldt);
		
		return taskAllocationRepository.save(taskAllocation);
	}
	
	public List<Map<String,Object>> getAllocationlist()
	{
		 return taskAllocationRepository.getAllocationlist();
	}
	
	@Transactional
	public TaskAllocation update(TaskAllocation taskAllocation,long id) 
	{
		Optional<TaskAllocation> op =taskAllocationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		taskAllocation.setAllocation_id(op.get().getAllocation_id());
		
		taskAllocation.setCompany_id(taskAllocation.getCompany_id());
		taskAllocation.setFin_year(taskAllocation.getFin_year());
		taskAllocation.setModified_type("INSERTED");
		taskAllocation.setInserted_on(op.get().getInserted_on());
		taskAllocation.setInserted_by(op.get().getInserted_by());
		taskAllocation.setUpdated_by(userRepository.getUserDetails(taskAllocation.getUsername()).getName());
		taskAllocation.setUpdated_on(ldt);
		taskAllocation.setDeleted_by("NA");
		taskAllocation.setDeleted_on(ldt);
	
		return taskAllocationRepository.save(taskAllocation);
	}
	
	@Transactional
	public TaskAllocation delete(TaskAllocation taskAllocation,long id) 
	{
		Optional<TaskAllocation> op = taskAllocationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		TaskAllocation task=op.get();
		
		task.setModified_type("DELETED");
		task.setInserted_by(op.get().getInserted_by());
		task.setInserted_on(op.get().getInserted_on());
		task.setUpdated_by(op.get().getUpdated_by());
		task.setUpdated_on(op.get().getUpdated_on());
		task.setDeleted_by(userRepository.getUserDetails(task.getUsername()).getName());
		task.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			task.setId(id);
		}
	  return taskAllocationRepository.save(task);
	}
	
	public TaskAllocation findOne(long id) 
	{
		Optional<TaskAllocation> op=taskAllocationRepository.findById(id);
		return op.get();
	}
	
}
