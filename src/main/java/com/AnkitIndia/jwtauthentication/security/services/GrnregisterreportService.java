package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;

import com.AnkitIndia.jwtauthentication.model.Grnregisterreport;
import com.AnkitIndia.jwtauthentication.model.Grnregisterreport_Dtls;
import com.AnkitIndia.jwtauthentication.model.Wheatreceiving_Dtls;
import com.AnkitIndia.jwtauthentication.responseDTO.GrnregisterreportDTO;

	public interface GrnregisterreportService {

	public List<Grnregisterreport> getGRNRegisterReportlist(String currDate,String finyear);

	public Grnregisterreport save(Grnregisterreport grnregisterreport);
	
	public Grnregisterreport update(Grnregisterreport grnregisterreport,long id);
	
	public Grnregisterreport delete(Grnregisterreport grnregisterreport,long id);
	
	public List<GrnregisterreportDTO> searchGRNRegisterReportPriview(String fromdate, String todate,String finyear);
	
	public List<Grnregisterreport> searchGRNRegisterReport(String fromdate, String todate,String finyear);
	
	public Grnregisterreport findOne(long id);
	
	public List<Grnregisterreport_Dtls> retriveGrnRegisterDetails(String wheatreceiveid);
	
	public List<GrnregisterreportDTO> grnRegisterAllDataList(String currDate,String finyear);
}
