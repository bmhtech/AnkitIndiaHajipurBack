package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Solar_power_generation_with_powercut;
import com.AnkitIndia.jwtauthentication.model.Status_table;

public interface Solar_power_generation_with_powercutService {
	
	public List<Map<String,Object>> solarPowerGenerationWithPowerCutList(String compid);

	public Solar_power_generation_with_powercut save(Solar_power_generation_with_powercut solar_power_generation_with_powercut);
	
	public Solar_power_generation_with_powercut update(Solar_power_generation_with_powercut solar_power_generation_with_powercut,long id);
	
	public Solar_power_generation_with_powercut findOne(long id);
	
	public List<Map<String,Object>> retriveSolarPowercutDetails(String powerid);
	
	public Solar_power_generation_with_powercut delete(Solar_power_generation_with_powercut solar_power_generation_with_powercut,long id);
	
	public List<Map<String,Object>> getSolarPowerWithPowerCutList(String bunit,String fromdate,String todate);
	
	public Status_table checkSolarPowerCut(String solardate);
	
}
