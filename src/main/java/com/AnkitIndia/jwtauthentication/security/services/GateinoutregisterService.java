package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Gateinoutregister;

public interface GateinoutregisterService {
	
	public List<Gateinoutregister> getGateinoutregister(String currDate,String finyear);

	public Gateinoutregister save(Gateinoutregister gateinoutregister);
	
	public Gateinoutregister update(Gateinoutregister gateinoutregister,long id);
	
	public Gateinoutregister delete(Gateinoutregister gateinoutregister,long id);
	
	public List<Gateinoutregister> searchgateinoutRegister(String fromdate, String todate,String finyear);
	
	public Gateinoutregister findOne(long id);
	

}
