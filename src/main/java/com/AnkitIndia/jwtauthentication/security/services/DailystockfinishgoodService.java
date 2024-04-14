package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood;
import com.AnkitIndia.jwtauthentication.model.Dailystockfinishgood_Dtls;
import com.AnkitIndia.jwtauthentication.model.Status_table;


public interface DailystockfinishgoodService {
	
	public List<Dailystockfinishgood> getdailystockfinishedgoodslist(String currDate,String finyear);
	
	public Dailystockfinishgood save(Dailystockfinishgood dailystockfinishgood);
	
	public Dailystockfinishgood update(Dailystockfinishgood dailystockfinishgood,long id);
	
	public Dailystockfinishgood delete(Dailystockfinishgood dailystockfinishgood,long id);
	
	public List<Dailystockfinishgood> searchdailystockfinishedgoods(String fromdate, String todate,String finyear);
	
	public Dailystockfinishgood findOne(long id);
	
	public List<Dailystockfinishgood_Dtls> getdailystockfinishedgoodsdtlslist(String dailystockid);
	
	public Dailystockfinishgood_Dtls getdailystockfinishedgoodopeningstock(String itemId,String date,String finyear);
	
	public Status_table getcheckdate(String date,String finyear);
	
	public List<Dailystockfinishgood_Dtls> getItemThruSalesThruBUanddDate(String bunit,String compid,String date,String finyear);

}
