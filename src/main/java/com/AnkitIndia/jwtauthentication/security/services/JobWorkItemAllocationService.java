package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.JobWorkItemAllocation;

public interface JobWorkItemAllocationService 
{
	public JobWorkItemAllocation save(JobWorkItemAllocation jobWorkItemAllocation);
	
	public List<Map<String, Object>> JobWorkItemAllocationList();
	
	public JobWorkItemAllocation findOne(long id);
	
	public JobWorkItemAllocation update(JobWorkItemAllocation jobWorkItemAllocation,long id);
	
	public JobWorkItemAllocation delete(JobWorkItemAllocation jobWorkItemAllocation,long id);
	
	public Map<String, Object> getItemQtythruLoading(String item,String party);
	
	public Map<String, Object> getJwAllocationRestWt(String party);
	
}
