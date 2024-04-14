package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import java.util.Map;

import com.AnkitIndia.jwtauthentication.model.Nongoodstypemaster;

public interface NongoodstypemasterService {
	
	public Nongoodstypemaster save(Nongoodstypemaster nongoodstypemaster);
	
	public List<Map<String, Object>> getNonGoodsTypeMasterList(String fin_year);
	
	public Nongoodstypemaster findOne(long id);
	
	public Nongoodstypemaster update(Nongoodstypemaster nongoodstypemaster, long id);
	
	public Nongoodstypemaster delete(Nongoodstypemaster nongoodstypemaster, long id);
	
	public List<Map<String, Object>> getServiceTypeList();
	
}
