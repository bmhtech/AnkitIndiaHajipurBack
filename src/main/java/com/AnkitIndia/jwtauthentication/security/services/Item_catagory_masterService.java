package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Item_catagory_master;
import com.AnkitIndia.jwtauthentication.model.Item_group_master;
import com.AnkitIndia.jwtauthentication.responseDTO.CompanyBusinessUnitMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Item_catagory_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.MessageStatusDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;;

public interface Item_catagory_masterService {
	
	public SequenceIdDTO getIcatSequenceId(String prefix,String company);

	public Item_catagory_master saveItemCategory(Item_catagory_master iCatagory_master);
	
	public List<Item_catagory_master> findAll(int psize,int pageNo);
	
	public List<Item_catagory_master> findItemCategories(String searchtext,String company);
	
	public List<Item_catagory_master> findAll(String company);
	
	public List<Item_catagory_master> findICategories(String company,String fin_year);
	
	public List<Item_catagory_masterDTO> getIcategory(String company);
	
	public Item_catagory_masterDTO getItemTypeByName(String iType);
	
	public Item_catagory_master findOne(long id);
	
	public Item_catagory_master update(Item_catagory_master iCatagory_master,long id);
	
	public Item_catagory_master deleteItemCategory(Item_catagory_master iCatagory_master,long id);
	
	public MessageStatusDTO chkCatNameStatus(String cname);
	
	public StatusDTO chkItemCatagoryCodeStatus(String code);
}
