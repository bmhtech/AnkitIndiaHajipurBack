package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.jwtauthentication.model.Wheat_issue_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatstock_Dtls;

public interface WheatreceivingService {
	
	public List<Wheatreceiving> getWheatReceivinglist(String currDate,String finyear);

	public Wheatreceiving save(Wheatreceiving wheatreceiving);
	
	public Wheatreceiving update(Wheatreceiving wheatreceiving,long id);
	
	public Wheatreceiving delete(Wheatreceiving wheatreceiving,long id);
	
	public List<Wheatreceiving> searchWheatreceiving(String fromdate, String todate,String finyear);
	
	public Wheatreceiving findOne(long id);
	
	public List<Wheatreceiving_Dtls> retriveWheatDetails(String wheatreceiveid);
	
	public List<Wheat_issue_Dtls> retriveWheatIssueDetails(String wheatreceiveid);
	
	public List<Wheatstock_Dtls> retrivewheatstock_Dtls(String wheatreceiveid);
	
	public Wheatstock_Dtls getopeningstockwheatrecieve(String itemId,String date,String finyear);

}
