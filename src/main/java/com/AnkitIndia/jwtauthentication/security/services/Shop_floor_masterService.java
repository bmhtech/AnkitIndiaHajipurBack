package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Shop_floor_master;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.Shop_floor_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

public interface Shop_floor_masterService {
	
	public SequenceIdDTO getSFSequenceId(String prefix,String company);
	
	public Shop_floor_master save(Shop_floor_master sFloor_master);

	public List<Shop_floor_master> findAllShopFloor();
	
	public Shop_floor_master findOne(long id);
	
	public List<Shop_floor_masterDTO> getShopFloors();
	
	public List<Shop_floor_masterDTO> getShopFloorThruBU(String bunit);
	
	public  List<Map<String,Object>> getShopFloorThruBUregular(String bunit);
	
	public  List<Map<String,Object>> getShopFloorspecialThruBU(String bunit);
	
	public Shop_floor_master update(Shop_floor_master sFloor_master,long id);
	
	public Shop_floor_master deleteShop_floor(Shop_floor_master sFloor_master,long id);
	
	public List<Shop_floor_master> findShop_floor(String searchtext);
	
	public StatusDTO checkShopFloorUsage(String shopfloorid);
	
}
