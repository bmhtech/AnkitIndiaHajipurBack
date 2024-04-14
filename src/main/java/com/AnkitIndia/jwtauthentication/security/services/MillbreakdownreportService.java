package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport;
import com.AnkitIndia.jwtauthentication.model.Millbreakdownreport_Dtls;

public interface MillbreakdownreportService {

	public List<Millbreakdownreport> getMillBreakdownlist(String currDate,String finyear);
	
	public Millbreakdownreport save(Millbreakdownreport millbreakdownreport);
	
	public Millbreakdownreport update(Millbreakdownreport millbreakdownreport,long id);
	
	public Millbreakdownreport delete(Millbreakdownreport millbreakdownreport,long id);
	
	public List<Millbreakdownreport> searcMillBreakdown(String fromdate, String todate,String finyear);
	
	public Millbreakdownreport findOne(long id);
	
	public List<Millbreakdownreport_Dtls> retriveMillBreakdownDetails(String millbreakdownid);
}
