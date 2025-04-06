package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Stack_maintain;


public interface Stack_maintainService {

	public List<Map<String,Object>> stackMaintainList(String currdate);
	
	public List<Map<String,Object>> getGrnList();
	
	public List<Map<String,Object>> getItemListByGrnId(String grnid);
	
	public List<Map<String,Object>> getPackingItemByGrn(String item,String grnid);
	
	public Stack_maintain save(Stack_maintain stack);
	
	public Stack_maintain update(Stack_maintain stack,long id);
	
	public Stack_maintain delete(Stack_maintain stack,long id);
	
	public Stack_maintain findOne(long id);
	
	public List<Map<String,Object>> stackItemRetriveList(String stackid);
	
	public List<Map<String,Object>> getGrnAllList();
	
	public List<Stack_maintain> findStackMaintain(String searchtext);
}