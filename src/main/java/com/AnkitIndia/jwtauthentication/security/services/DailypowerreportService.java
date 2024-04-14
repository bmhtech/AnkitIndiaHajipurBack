package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Dailypowerreport;

public interface DailypowerreportService {
	
	public List<Dailypowerreport> getdailypowerreportlist(String currDate,String finyear);

	public Dailypowerreport save(Dailypowerreport dailypowerreport);
	
	public Dailypowerreport update(Dailypowerreport dailypowerreport,long id);
	
	public Dailypowerreport delete(Dailypowerreport dailypowerreport,long id);
	
	public List<Dailypowerreport> searchdailypowerreport(String fromdate, String todate,String finyear);
	
	public Dailypowerreport findOne(long id);
	
}
