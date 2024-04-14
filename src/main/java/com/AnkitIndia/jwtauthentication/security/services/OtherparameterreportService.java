package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Otherparameterreport;
import com.AnkitIndia.jwtauthentication.model.Otherparameterreport_Dtls;

public interface OtherparameterreportService {

	public List<Otherparameterreport> getOtherParameterlist(String currDate,String finyear);
	
	public Otherparameterreport save(Otherparameterreport otherparameterreport);
	
	public Otherparameterreport update(Otherparameterreport otherparameterreport,long id);
	
	public Otherparameterreport delete(Otherparameterreport otherparameterreport,long id);
	
	public List<Otherparameterreport> searcOtherparameter(String fromdate, String todate,String finyear);
	
	public Otherparameterreport findOne(long id);
	
	public List<Otherparameterreport_Dtls> retriveOtherParameterDetails(String otherparameterid);
}
