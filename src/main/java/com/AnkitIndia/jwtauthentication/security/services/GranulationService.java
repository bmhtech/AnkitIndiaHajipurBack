package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Granulation;
import com.AnkitIndia.jwtauthentication.model.Granulation_Dtls;
import com.AnkitIndia.jwtauthentication.model.Seives_Dtls;

public interface GranulationService {

	public List<Granulation> getGranulationlist(String currDate,String finyear);
	
	public Granulation save(Granulation granulation);
	
	public Granulation update(Granulation granulation,long id);
	
	public Granulation delete(Granulation granulation,long id);
	
	public List<Granulation> searchGranulationReport(String fromdate, String todate,String finyear);
	
	public Granulation findOne(long id);
	
	public List<Granulation_Dtls> retriveGranulationDetails(String granulationreportid);
	
	public List<Seives_Dtls> getSeiveslistByitemid(String itemid);
}
