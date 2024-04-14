package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Wheatacceptance;

public interface WheatacceptanceService {

	public List<Wheatacceptance> getWheatAcceptancelist(String currDate,String finyear);
	
	public Wheatacceptance save(Wheatacceptance wheatacceptance);
	
	public Wheatacceptance update(Wheatacceptance wheatacceptance,long id);
	
	public Wheatacceptance delete(Wheatacceptance wheatacceptance,long id);
	
	public List<Wheatacceptance> searchWheatAcceptance(String fromdate, String todate,String finyear);
	
	public Wheatacceptance findOne(long id);
	
	public List<Wheatacceptance> getWheatAcceptancePrintList(String acceptanceid);
}
