package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.GodownMaster;
import com.AnkitIndia.jwtauthentication.model.Item_Service_master;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;

public interface Item_Service_masterService {

	public SequenceIdDTO getItemServiceSequenceId(String company,String fin_year);
	
	public List<Map<String, Object>> getItemServiceList(String company);
	
	public List<Item_Service_master> findItemServiceMaster(String searchtext,String company);
	
	public Item_Service_master findOne(long id);
	
	public MessageStatusDTO chkItemServiceNameStatic(String cname);
	
	public Item_Service_master save(Item_Service_master item_Service_master);
	
	public Item_Service_master update(Item_Service_master item_Service_master,long id);
	
	public Item_Service_master delete(Item_Service_master item_Service_master,long id);
	
	
	
	
	
}
