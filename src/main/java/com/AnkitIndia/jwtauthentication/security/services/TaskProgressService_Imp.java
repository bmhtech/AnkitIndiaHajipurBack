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
import com.AnkitIndia.jwtauthentication.model.Delivery_challan;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.TaskProgress;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.TaskAllocationRepository;
import com.AnkitIndia.jwtauthentication.repository.TaskProgressRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

@Service
public class TaskProgressService_Imp  implements TaskProgressService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskProgressRepository taskProgressRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	TaskAllocationRepository taskAllocationRepository;
	
	@Transactional
	public TaskProgress save(TaskProgress taskProgress)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		 int slno=0;
		 
		    String sno=taskProgressRepository.countId();
			
			if(sno != null )
			{
				slno=Integer.parseInt(sno);
			}
			
			String gen_sno=UniqueIDTransaction.uniqueid("TMP",slno);
			taskProgress.setTask_id(gen_sno);
			
			if(Utility.isNullOrEmpty(taskProgress.getTask_name())) {
				taskProgress.setTask(taskAllocationRepository.getTaskName(taskProgress.getTask_name()).getTask_name());
			}else{taskProgress.setTask("0");};
			
			taskProgress.setModified_type("INSERTED");
			taskProgress.setInserted_by(userRepository.getUserDetails(taskProgress.getUsername()).getName());
			taskProgress.setInserted_on(ldt);
			taskProgress.setUpdated_by("NA");
			taskProgress.setUpdated_on(ldt);
			taskProgress.setDeleted_by("NA");
			taskProgress.setDeleted_on(ldt);
		
		return taskProgressRepository.save(taskProgress);
	}
	
	public List<Map<String,Object>> getProgresslist()
	{
		 return taskProgressRepository.getProgresslist();
	}
	
	@Transactional
	public TaskProgress update(TaskProgress taskProgress,long id) 
	{
		Optional<TaskProgress> op =taskProgressRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		taskProgress.setTask_id(op.get().getTask_id());
		
		if(Utility.isNullOrEmpty(taskProgress.getTask_name())) {
			taskProgress.setTask(taskAllocationRepository.getTaskName(taskProgress.getTask_name()).getTask_name());
		}else{taskProgress.setTask("0");};
		
		taskProgress.setCompany_id(taskProgress.getCompany_id());
		taskProgress.setFin_year(taskProgress.getFin_year());
		taskProgress.setModified_type("INSERTED");
		taskProgress.setInserted_on(op.get().getInserted_on());
		taskProgress.setInserted_by(op.get().getInserted_by());
		taskProgress.setUpdated_by(userRepository.getUserDetails(taskProgress.getUsername()).getName());
		taskProgress.setUpdated_on(ldt);
		taskProgress.setDeleted_by("NA");
		taskProgress.setDeleted_on(ldt);
	
		return taskProgressRepository.save(taskProgress);
	}
	
	@Transactional
	public TaskProgress delete(TaskProgress taskProgress,long id) 
	{
		Optional<TaskProgress> op = taskProgressRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		TaskProgress task=op.get();
		
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
	  return taskProgressRepository.save(task);
	}
	
	public TaskProgress findOne(long id) 
	{
		Optional<TaskProgress> op=taskProgressRepository.findById(id);
		return op.get();
	}
	
	public List<Map<String,Object>> getAllocationUsernameWiselist(String currdate,String user)
	{
		Employee_master emp=employeeMasterRepository.getEmployeebyuser(user);
		String empcode=emp.getEmp_id();
		//System.out.println("currdate:"+currdate+" user:"+user+" emp:"+empcode);
		 return taskAllocationRepository.getAllocationUsernameWiselist(empcode,currdate);
	}
	
	public Map<String,Object> getTaskNameDetails(String taskid)
	{
		 return taskAllocationRepository.getTaskNameDetails(taskid);
	}
	
	public List<Map<String,Object>> getTaskProgressReport(String fromdate,String todate)
	{
		 return taskProgressRepository.getTaskProgressReport(fromdate,todate);
	}
	
}
