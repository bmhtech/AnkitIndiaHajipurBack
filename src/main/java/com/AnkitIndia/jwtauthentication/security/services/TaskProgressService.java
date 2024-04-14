package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.TaskProgress;

public interface TaskProgressService {
	
	public List<Map<String,Object>> getProgresslist();
	
	public TaskProgress save(TaskProgress taskProgress);
	
	public TaskProgress update(TaskProgress taskProgress,long id);
	
	public TaskProgress delete(TaskProgress taskProgress,long id);
	
	public TaskProgress findOne(long id);
	
	public List<Map<String,Object>> getAllocationUsernameWiselist(String currdate,String user);
	
	public Map<String,Object> getTaskNameDetails(String taskid);
	
	public List<Map<String,Object>> getTaskProgressReport(String fromdate,String todate);
}
