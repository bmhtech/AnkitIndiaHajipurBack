package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Warehouse_master;
import com.AnkitIndia.jwtauthentication.responseDTO.LedgermasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.WarehouseMasterDTO;




public interface WarehouseMasterService {
	
	public Warehouse_master save(Warehouse_master warehouse);
	
	public List<Warehouse_master> findAll();
	
	public List<Warehouse_master> getWareHouseNCList();
	
	public Warehouse_master findOne(long id);
	
	public List<Map<String, Object>> warehouseStackRetriveList(String warehouse_id);
	
	public Warehouse_master update(Warehouse_master warehouse,long id);
	
	public Warehouse_master deleteWarehouse(Warehouse_master warehouse,long id);
	
	public List<WarehouseMasterDTO> getWarehouseNameCode();
	
	public WarehouseMasterDTO getWHNListbyCode(String whCode);
	
	public List<WarehouseMasterDTO> getWHListbyBUnit(String bunit);
	
	public List<Map<String, Object>> getWHListbyBUnitFastApi(String bunit);
	
	public List<WarehouseMasterDTO> getAllWarehouse();
	
	public List<Map<String, Object>> getStackNoByWarehouse(String wh_code);
	
	public Map<String, Object> getStackUom(String rack);
	
	public SequenceIdDTO getWareHouseSequenceId(String prefix,String company);
	
	public List<Warehouse_master> findAllWarehouse(String searchtext);
}
