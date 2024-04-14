package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Item_stock;


public interface Item_stockService {

    public List<Map<String,Object>> getStocklist();
	
    public List<Map<String,Object>> getAllItemFromStockView();
    
	public Item_stock save(Item_stock item_stock);
	
	public Item_stock update(Item_stock item_stock,long id);
	
	public Item_stock findOne(long id);
	
	public List<Map<String,Object>> retriveStockDetails(String stockid);
}
