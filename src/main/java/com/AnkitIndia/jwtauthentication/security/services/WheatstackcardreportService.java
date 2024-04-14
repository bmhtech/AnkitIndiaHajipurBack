package com.AnkitIndia.jwtauthentication.security.services;


import java.util.List;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreport;
import com.AnkitIndia.jwtauthentication.model.Wheatstackcardreportdetails;
import com.AnkitIndia.jwtauthentication.responseDTO.WheatstackcardreportDTO;

public interface WheatstackcardreportService {
	
	public Wheatstackcardreport save(Wheatstackcardreport wheatstackcardreport);
	
	public List<WheatstackcardreportDTO> getwheatstackcardlist(String finyear);
	
	public Wheatstackcardreport retrievewheatstackcard(long id);
	
	public List<Wheatstackcardreportdetails> retrievewheatstackcard_details(String wheatstackid);
	
	public Wheatstackcardreport update(Wheatstackcardreport wheatstackcardreport,long id);
	
	
	public Wheatstackcardreport delete(Wheatstackcardreport wheatstackcardreport,long id);

}
