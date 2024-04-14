package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Misclabreportfg;
import com.AnkitIndia.jwtauthentication.model.Misclabreportfg_Dtls;

public interface MisclabreportfgService {
	
	public List<Misclabreportfg> getLabReportlist(String currDate,String finyear);
	
	public Misclabreportfg save(Misclabreportfg misclabreportfg);
	
	public Misclabreportfg update(Misclabreportfg misclabreportfg,long id);
	
	public Misclabreportfg delete(Misclabreportfg misclabreportfg,long id);
	
	public List<Misclabreportfg> searcLabReport(String fromdate, String todate,String finyear);
	
	public Misclabreportfg findOne(long id);
	
	public List<Misclabreportfg_Dtls> retriveLabReportDetails(String misclabreportfgid);

}
