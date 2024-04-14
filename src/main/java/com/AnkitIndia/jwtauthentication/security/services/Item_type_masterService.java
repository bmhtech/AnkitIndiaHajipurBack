package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Item_type_master;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_type_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Item_type_masterService {

	public SequenceIdDTO getItypeSequenceId(String prefix,String company);
	
	public Item_type_master saveItemType(Item_type_master iType_master);
	
	public List<Item_type_master> getItemtypes(String company);
	
	public List<Item_type_master> findItemTypes(String searchtext,String company);
	
	public List<Item_type_masterDTO> getItemtypes(int psize,int pageNo);
	
	public Item_type_master findOne(long id);
	
	public List<Item_type_masterDTO> getItemType(String company);
	
	public List<Item_type_masterDTO> getItemTypesNew(String company);
	
	public List<Map<String,Object>> itemTypeListFastAPI(String company);
	
	public List<Item_type_masterDTO> getItemTypeMaster(String company);
	
	public Item_type_masterDTO getItemTypeNameByCode(String iType);
	
	public Item_type_master update(Item_type_master iType_master,long id);
	
	public Item_type_master deleteItemTypes(Item_type_master iType_master,long id);
	
	public void deleteItemType(Item_type_master iType_master);
	
	public StatusDTO chkItemTypeCodeStatus(String code);
	
}
