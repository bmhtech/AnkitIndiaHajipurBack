package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Solar_power_generation;
import com.AnkitIndia.jwtauthentication.model.Status_table;

public interface Solar_power_generationService {

	public List<Map<String,Object>> getSolarPowerGeneration(String compid);
	
	public Solar_power_generation save(Solar_power_generation solar_power_generation);
	
	public Solar_power_generation update(Solar_power_generation solar_power_generation,long id);
	
	public Solar_power_generation findOne(long id);
	
	public List<Map<String,Object>> getSolarPorGenReport(String bunit,String fromdate,String todate);
	
	public List<Map<String,Object>> getInverterSolarPowerGeneration(String bunit,String fromdate,String todate);
	
	public List<Map<String,Object>> getAllSolarData(String solar_date);
	
	public Solar_power_generation delete(Solar_power_generation solar_power_generation,long id);
	
	public Status_table checkSolarPowerDate(String solardate);
	
}
