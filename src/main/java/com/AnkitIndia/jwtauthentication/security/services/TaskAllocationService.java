package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.TaskAllocation;

public interface TaskAllocationService {

	public List<Map<String,Object>> getAllocationlist();
	
	public TaskAllocation save(TaskAllocation taskAllocation);
	
	public TaskAllocation update(TaskAllocation taskAllocation,long id);
	
	public TaskAllocation delete(TaskAllocation taskAllocation,long id);
	
	public TaskAllocation findOne(long id);
	
}
