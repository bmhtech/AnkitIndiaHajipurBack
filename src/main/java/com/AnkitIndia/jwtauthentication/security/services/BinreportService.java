package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Binreport;
import com.AnkitIndia.jwtauthentication.model.Binreportdetails;
import com.AnkitIndia.jwtauthentication.responseDTO.BinreportdetailspopupDTO;


public interface BinreportService  {
	
	public Binreport save(Binreport binreport);

	public Binreport update(Binreport binreport,long id);
	
	public Binreport delete(Binreport binreport,long id);
	
	public List<Binreportdetails> getbinreportlist(String businessunit_id,String finyear);
	
	public Binreport findOne(long id);
	
	public List<Binreportdetails> retrivebillreportDetails(String binreportid);
	
	public List<BinreportdetailspopupDTO> retrivebillreportcolumnDetails(String binreportid);
	
	
	public List<Binreport> getbillreportlist(String finyear);

}
