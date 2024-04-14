package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Dailyweigher;

	public interface DailyweigherService {
		
	public List<Map<String,Object>> getDailyweigherList();
	
	public Dailyweigher save(Dailyweigher dailyweigher);
	
	public Dailyweigher update(Dailyweigher dailyweigher,long id);
	
	public Dailyweigher findOne(long id);
	
	public List<Map<String,Object>> retriveDailyweigherDetails(String dwg_id);
	
	public Dailyweigher delete(Dailyweigher dailyweigher,long id);
	
	public List<Map<String,Object>> getDailyWeigherReport(String wdate,String machine);

}
